package com.spxts.tdms.server.service;

import java.util.HashMap;
import java.util.List;

import com.spxts.tdms.server.dto.DBFFileTrackDTO;

public interface WaukeshaTransformerService {

	public String[] getHomePageList();

	public DBFFileTrackDTO trackDBF(String fileName);

	public DBFFileTrackDTO updateStatus(String fileName, long recordId, boolean status);

	public DBFFileTrackDTO generateNewVersion(String fileName);

	public DBFFileTrackDTO updateRecord(String fileName, HashMap<String, String> record);

	public DBFFileTrackDTO addRecordList(String fileName, List<HashMap<String, String>> record);

	public DBFFileTrackDTO deleteRecords(String fileName, List<String> recordsList);
}
