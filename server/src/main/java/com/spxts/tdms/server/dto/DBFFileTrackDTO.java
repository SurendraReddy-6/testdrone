package com.spxts.tdms.server.dto;

import java.util.HashMap;
import java.util.List;

public class DBFFileTrackDTO {

	private List<HashMap<String, String>> oldRecordsList;
	private List<HashMap<String, String>> newRecordsList;
	private HashMap<String, String> updatedRecord;
	private List<HashMap<String, String>> deletedRecord;
	private int newRecords;
	
	public List<HashMap<String, String>> getOldRecordsList() {
		return oldRecordsList;
	}
	public void setOldRecordsList(List<HashMap<String, String>> oldRecordsList) {
		this.oldRecordsList = oldRecordsList;
	}
	public List<HashMap<String, String>> getNewRecordsList() {
		return newRecordsList;
	}
	public void setNewRecordsList(List<HashMap<String, String>> newRecordsList) {
		this.newRecordsList = newRecordsList;
	}
	public int getNewRecords() {
		return newRecords;
	}
	public void setNewRecords(int newRecords) {
		this.newRecords = newRecords;
	}
	public HashMap<String, String> getUpdatedRecord() {
		return updatedRecord;
	}
	public void setUpdatedRecord(HashMap<String, String> updatedRecord) {
		this.updatedRecord = updatedRecord;
	}
	public List<HashMap<String, String>> getDeletedRecord() {
		return deletedRecord;
	}
	public void setDeletedRecord(List<HashMap<String, String>> deletedRecord) {
		this.deletedRecord = deletedRecord;
	}
}
