package com.eval.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eval.dto.EvalRequest;
import com.eval.dto.EvalResponse;

@Component
public class CountChecker {
	@Autowired
	ParaReader paraReader;
	
	public EvalResponse getCount(EvalRequest request){
		String para = paraReader.getPara("para.txt");
		System.out.println("=======" + para);
		
		ArrayList<String> words = getWordList(request);
		
		EvalResponse response = new EvalResponse();
		response.setCounts(populateCount(para, words));
		
		return response;
	}

	private HashMap<String, Integer> populateCount(String para, ArrayList<String> words) {
		HashMap<String, Integer> wordCount = new HashMap<>();
		
		if(words != null){
			String[] arr;
			int count = 0;
			for(String word : words){
				count = 0;
				arr = para.split(word);
				
				if(arr != null && arr.length > 0)
					count = arr.length - 1;
				
				wordCount.put(word, count);
			}
		}
		
		return wordCount;
	}
	
	public ArrayList<String> getWordList(EvalRequest request){
		return request.getSearchText();
	}
}
