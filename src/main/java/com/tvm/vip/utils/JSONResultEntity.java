package com.tvm.vip.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONResultEntity<T> {

	private boolean success;
	private String message;
	private List<String> errors = new ArrayList<String>();
	private String resultNote;
	public Map<Object, Object> resultsMap;

	public JSONResultEntity(boolean success, String message, List<String> errors, Map<Object, Object> results,
			Long totalRecords, Long totalPages) {
		this.success = success;
		this.message = message;
		this.errors = errors;
		this.resultsMap = results;
	}

	public JSONResultEntity(boolean success, String message, List<String> errors, Map<Object, Object> results) {
		this.success = success;
		this.message = message;
		this.errors = errors;
		this.resultsMap = results;
	}

	public JSONResultEntity(boolean success, String message, Map<Object, Object> results, String resultNote) {
		this.success = success;
		this.message = message;
		this.resultNote = resultNote;
		this.resultsMap = results;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Map<Object, Object> getResultsMap() {
		return resultsMap;
	}

	public void setResultsMap(Map<Object, Object> resultsMap) {
		this.resultsMap = resultsMap;
	}

	public String getResultNote() {
		return resultNote;
	}

	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}
}
