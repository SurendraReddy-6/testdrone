package com.spxts.tdms.agent.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.spxts.tdms.agent.dto.DBFDTO;
import com.spxts.tdms.agent.service.DBFTrackerService;

@Service
public class DBFTrackerServiceImpl implements DBFTrackerService {

	@Value("${source.DBF_INPUT_FILES}")
	private String dbfInputFolder;

	public static List<HashMap<String, String>> DBF_DATA = new ArrayList<HashMap<String, String>>();

	public static HashMap<String, Long> TRACK_DBT = new HashMap<String, Long>();

	@Override
	public String[] getHomePageList() {
		File directoryPath = new File(dbfInputFolder);
		String listFolders[] = directoryPath.list();
		return listFolders;

	}

	@Override
	public DBFDTO trackDBF(String fileId) {
		DBFDTO dto = new DBFDTO();

		List<HashMap<String, String>> array = new ArrayList<HashMap<String, String>>();
		File file = getFile(fileId);

		Timestamp modifiedTime = new Timestamp(file.lastModified());
		Timestamp presentTime = new Timestamp(System.currentTimeMillis());

		long diff = presentTime.getTime() - modifiedTime.getTime();

		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

//		if (minutes <= 1) {
//		array = getTrackDataForDBF(file.getAbsolutePath(),fileId);
//		}else{  flag - row added 6,7 (only added)
//		if (TRACK_DBT.get(fileId) != null && array.size() > TRACK_DBT.get(fileId)) {// 10 >5 ==> 10-5 integer number
//			TRACK_DBT.put(fileId, (long) array.size());
//			addedRecords = (int) (array.size() - TRACK_DBT.get(fileId));
//		} else {
//			TRACK_DBT.put(fileId, (long) array.size());
//		}

		dto = getTrackDataForDBF(file.getAbsolutePath(), fileId);
		return dto;
	}

	private File getFile(String dbfId) {

		List<File> fileList = new ArrayList<File>();
		File directoryPath = new File(dbfInputFolder);
		String folders[] = directoryPath.list();

		for (int i = 0; i < folders.length; i++) {
			if (folders[i].toString().equals(dbfId)) {
				File filesList = new File(directoryPath + "/" + dbfId);
				String files[] = filesList.list();
				for (int j = 0; j < files.length; j++) {
					if (files[j].toString().startsWith(dbfId) && files[j].toString().endsWith(".dbf")) {
						fileList.add(new File(directoryPath + "/" + dbfId + "/" + files[j].toString()));
					}
				}
			}
		}
		return fileList.get(0);// need to send a list of files
	}

	private DBFDTO getTrackDataForDBF(String path, String fileId) {

		List<HashMap<String, String>> oldList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

		DBFDTO dbfDTO = new DBFDTO();
		JSONArray jsonArray = new JSONArray();

		try {
			InputStream inputStream = new FileInputStream(path);

			DBFReader reader = new DBFReader(inputStream);
			int numberOfFields = reader.getFieldCount();

			List<Object> headers = new ArrayList<Object>();
			for (int i = 0; i < numberOfFields; i++) {
				DBFField field = reader.getField(i);
				headers.add(field.getName());
			}

			Object[] rowObjects;
			int id = 0;
			while ((rowObjects = reader.nextRecord()) != null) {
				List<Object> singleRow = new ArrayList<Object>();
				JSONObject object = new JSONObject();
				HashMap<String, String> map = new HashMap<String, String>();
				int headerCount = 0;

				for (int i = 0; i < rowObjects.length; i++) {
					singleRow.add(rowObjects[i]);
					map.put(((String) headers.get(headerCount)), String.valueOf(rowObjects[i]).trim());
					// for json file
					object.put(((String) headers.get(headerCount)), String.valueOf(rowObjects[i]).trim());
					headerCount++;
				}

				map.put("id", String.valueOf(id++));
				map.put("testSession", "false");

				object.put("id", String.valueOf(id++));
				object.put("testSession", "false");

				int sum = oldList.size();
				if (TRACK_DBT.get(fileId) != null && sum > TRACK_DBT.get(fileId)) {
					newList.add(map);
					TRACK_DBT.put(fileId, (long) newList.size());
				} else {
					oldList.add(map);
					TRACK_DBT.put(fileId, (long) oldList.size());
				}
				jsonArray.add(object);
			}

			dbfDTO.setNewRecordsList(newList);
			dbfDTO.setOldRecordsList(oldList);
			dbfDTO.setNewRecords(newList.size());

			convertJSONFile(jsonArray, fileId, getLatestVersion(fileId));// json create

			System.out.println(reader.getFieldCount() + " === " + reader.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbfDTO;
	}

	private void convertJSONFile(JSONArray jsonArray, String fileName, int version) {
		try {
			FileWriter file = new FileWriter(dbfInputFolder + "/" + fileName.split("_")[0] + "/"
					+ fileName.split("_")[0] + "_V" + version + ".json");
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DBFDTO updateStatus(String fileName, String recordId, boolean status) {
		DBFDTO dto = new DBFDTO();
		try {
			File file = new File(dbfInputFolder + "/" + fileName.split("_")[0] + "/" + fileName + ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));

				ObjectMapper mapper = new ObjectMapper();
				
				List<HashMap<String, String>> mapList = mapper.readValue(jsonArray.toString(),new TypeReference<List<HashMap<String, String>>>(){});
				
				if(mapList.size()!=jsonArray.size()) {
					return null;
				}
				
				for (int i = 0; i < mapList.size(); i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					if (mapList.get(i).get("id") != null && mapList.get(i).get("id").toString().equals(recordId)) {
						
						HashMap<String, String> record = mapList.get(i);
						record.put("testSession", "true");
						mapList.remove(i);
						dto.setOldRecordsList(mapList);
						dto.setUpdatedRecord(record);
						
						object.replace("testSession", "true");
						jsonArray.remove(i);
						jsonArray.add(i, object);

						convertJSONFile(jsonArray, fileName, getLatestVersion(fileName));// write json
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public DBFDTO deleteRecord(String fileName, String recordId) {
		DBFDTO dto = new DBFDTO();
		try {
			File file = new File(dbfInputFolder + "/" + fileName.split("_")[0] + "/" + fileName + ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));
				
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> mapList = mapper.readValue(jsonArray.toString(),new TypeReference<List<HashMap<String, String>>>(){});
				
				if(mapList.size()!=jsonArray.size()) {
					return null;
				}
				for (int i = 0; i < mapList.size(); i++) {
					if (mapList.get(i).get("id") != null && mapList.get(i).get("id").toString().equals(recordId)) {
						HashMap<String, String> record = mapList.get(i);
						mapList.remove(i);
						dto.setOldRecordsList(mapList);
						dto.setDeletedRecord(record);
						
						jsonArray.remove(i);
						convertJSONFile(jsonArray, fileName, getLatestVersion(fileName));// write json
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public DBFDTO generateNewVersion(String fileName) {
		DBFDTO dto = new DBFDTO();
		try {
			JSONArray result = new JSONArray();
			File file = new File(dbfInputFolder + "/" + fileName.split("_")[0] + "/" + fileName + ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));

				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					object.replace("testSession", "false");
					result.add(object);
				}
				int version = getLatestVersion(fileName);
				convertJSONFile(result, fileName, (version + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private int getLatestVersion(String fileName) {
		try {
			File directoryPath = new File(dbfInputFolder + "/" + fileName.split("_")[0]);
			String listFolders[] = directoryPath.list();
			int count = 0;
			int large = 0;
			for (String string : listFolders) {
				if (string.endsWith(".json")) {
					int id = Integer.parseInt((string.split("_")[1]).replace("V", "").replace(".json", ""));
					if (large < id) {
						large = id;
					}
					count++;
				}
			}
			if (count == 0) {
				return 1;
			}
			return large;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
}