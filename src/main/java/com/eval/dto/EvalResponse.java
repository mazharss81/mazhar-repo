package com.eval.dto;

import java.io.Serializable;
import java.util.HashMap;

public class EvalResponse implements Serializable {

	private static final long serialVersionUID = -5384257604874897608L;
	
	public String status;
	
	public String errorMessage;

	public HashMap<String, Integer> counts;

	public HashMap<String, Integer> getCounts() {
		return counts;
	}

	public void setCounts(HashMap<String, Integer> counts) {
		this.counts = counts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
