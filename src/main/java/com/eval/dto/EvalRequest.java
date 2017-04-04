package com.eval.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class EvalRequest implements Serializable {

	private static final long serialVersionUID = -1952860820519925679L;

	public ArrayList<String> searchText;

	public ArrayList<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(ArrayList<String> searchText) {
		this.searchText = searchText;
	}
	
	
}
