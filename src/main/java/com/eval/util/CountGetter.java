package com.eval.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.eval.dto.WordDetail;

@Component
public class CountGetter {

	public String readParaAndReturnCount(int topCount) {
		String path = "para.txt";
		Scanner scanner = null;

		// Build map of word and wordDetails POJO
		// WordDetail POJO will keep track of word count
		HashMap<String, WordDetail> wordDetailMap = new HashMap<String, WordDetail>();
		StringBuilder builder = new StringBuilder();
		try {
			Resource resource = new ClassPathResource(path);
			scanner = new Scanner(resource.getInputStream());
			scanner.useDelimiter(" +");

			String word;

			while (scanner.hasNext()) {
				word = scanner.next();
				word = word.replace(".", "");
				WordDetail wordDetail = new WordDetail(word, 1);

				if (wordDetailMap.containsKey(wordDetail.getWord())) {
					WordDetail tempWordDetail = wordDetailMap.get(wordDetail.getWord());
					tempWordDetail.setCount(tempWordDetail.getCount() + 1);
				} else {
					wordDetailMap.put(wordDetail.getWord(), wordDetail);
				}
			}

			System.out.println("***** BEFORE SORT ******");
			printWordMap(wordDetailMap);

			// Now get the values from Map and sort them using comparator
			Collection<WordDetail> wordValues = wordDetailMap.values();			
			List<WordDetail> wordDetailList = new LinkedList<WordDetail>(wordValues);
			Collections.sort(wordDetailList, new WordDetailComprator());

			int counter = 0;
			System.out.println("\n\n***** AFTER SORT ******");
			for (WordDetail wordDetail : wordDetailList) {
				System.out.println(wordDetail);
				builder.append(wordDetail.getWord()).append("|").append(wordDetail.getCount());
				builder.append(" ,");

				if (++counter == topCount)
					break;
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
			builder.append("Error occured, please try again");
		} finally {
			closeResource(scanner);
		}

		return builder.toString();
	}

	private void printWordMap(HashMap<String, WordDetail> wordDetailMap) {
		for (Map.Entry<String, WordDetail> mapEntry : wordDetailMap.entrySet()) {
			System.out.println(mapEntry.getKey() + "::=>" + mapEntry.getValue());
		}
	}

	private void closeResource(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException ioe) {
				System.out.println("error while closing resource.");
			}
		}
	}

}
