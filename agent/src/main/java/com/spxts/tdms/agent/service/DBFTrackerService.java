package com.spxts.tdms.agent.service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.spxts.tdms.agent.dto.DBFDTO;

public interface DBFTrackerService {

	DBFDTO trackDBF(String fileId);

	String[] getHomePageList();

	DBFDTO updateStatus(String recordId, String recordId2, boolean status);

	DBFDTO deleteRecord(String fileName, String recordId);

	DBFDTO generateNewVersion(String fileName);

}
