package com.eval.util;

import java.util.Comparator;

import com.eval.dto.WordDetail;

public class WordDetailComprator implements Comparator<WordDetail>{

	@Override
	public int compare(WordDetail wordDetail1, WordDetail wordDetail2) {		
		return wordDetail2.getCount() - wordDetail1.getCount();
	} 
}
