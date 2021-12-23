package com.spxts.tdms.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.spxts.tdms.server.dto.DBFFileTrackDTO;
import com.spxts.tdms.server.enitity.ImpulseTestResult;
import com.spxts.tdms.server.repository.ImpulseTestResultsRepository;
import com.spxts.tdms.server.service.WaukeshaTransformerService;

@Service
@SuppressWarnings("unchecked")
public class WaukeshaTransformerServiceImpl implements WaukeshaTransformerService {

	@Autowired
	private ImpulseTestResultsRepository impulseTestRepository;
	
	public static HashMap<String, Long> TRACK_DBT = new HashMap<String, Long>();

	@Value("${source.DTAX_SHARED_FOLDER}")
	private String dtaxSharedFolder;
	
	@Autowired
	private TransformerServiceImpl transformerServiceImpl;
	
	@Override
	public String[] getHomePageList() {
		File directoryPath = new File(dtaxSharedFolder);
		String listFolders[] = directoryPath.list();
		return listFolders;
	}

	@Override
	public DBFFileTrackDTO trackDBF(String fileName) {
		List<File> dbfFilesList = transformerServiceImpl.getOutputProcessFile(fileName,".dbf");
		
		DBFFileTrackDTO dto  = getTrackDataForDBF(dbfFilesList.get(0).getAbsolutePath(), fileName);
		
		addJsonToDb(fileName,dto.getOldRecordsList());
		return dto;
	}
	
	private void addJsonToDb(String fileName,List<HashMap<String, String>> recordsList) {
		ImpulseTestResult impulseTest = new ImpulseTestResult();

		impulseTest.setSerialNumber(fileName);
		impulseTest.setLocked(0);
		impulseTest.setLastCreatedBy(fileName);
		impulseTest.setLastCreatedDate(new DateTime().getMillis());
		impulseTest.setLastModifiedBy(fileName);
		impulseTest.setLastModifiedDate(new DateTime().getMillis());
		
		impulseTest.setJsonTest(recordsList.toString());
		
		impulseTestRepository.save(impulseTest);
	}

	private DBFFileTrackDTO getTrackDataForDBF(String path, String fileId) {

		List<HashMap<String, String>> oldList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

		DBFFileTrackDTO dbfDTO = new DBFFileTrackDTO();
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
				JSONObject object = new JSONObject();
				HashMap<String, String> map = new HashMap<String, String>();
				int headerCount = 0;
				for (int i = 0; i < rowObjects.length; i++) {
					map.put(((String) headers.get(headerCount)), String.valueOf(rowObjects[i]).trim());
					// for json file
					object.put(((String) headers.get(headerCount)), String.valueOf(rowObjects[i]).trim());
					headerCount++;
				}

				map.put("id", String.valueOf(id++));
				map.put("testSession", "false");
				map.put("ignore", "false");
				map.put("active", "true");
				map.put("isNew", "false");
				
				object.put("id", String.valueOf(id++));
				object.put("testSession", "false");
				object.put("ignore", "false");
				object.put("active", "true");
				object.put("isNew", "false");

				int sum = oldList.size()+1;
				//track 
				if (TRACK_DBT.get(fileId) != null && sum > TRACK_DBT.get(fileId)) {
					newList.add(map);
				} else {
					oldList.add(map);
				}
				jsonArray.add(object);
			}

			dbfDTO.setNewRecordsList(newList);
			dbfDTO.setOldRecordsList(oldList);
			dbfDTO.setNewRecords(newList.size());

			TRACK_DBT.put(fileId,(long) reader.getRecordCount());
			
			convertJSONFile(jsonArray, fileId, getLatestVersion(fileId));// json create

			System.out.println(reader.getFieldCount() + " === " + reader.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbfDTO;
	}

	private void convertJSONFile(JSONArray jsonArray, String fileName, int version) {
		try {
			String path = "";
			if(version == 0) {
				path = dtaxSharedFolder + "/" + fileName + "/"+ fileName + "_V1.json";
			}else {
				path = dtaxSharedFolder + "/" + fileName + "/"+ fileName + "_V" + version + ".json";
			}
			FileWriter file = new FileWriter(path);
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int getLatestVersion(String fileName) {
		try {
			File directoryPath = new File(dtaxSharedFolder + "/" + fileName);
			String listFolders[] = directoryPath.list();
			int large = 0;
			for (String string : listFolders) {
				if (string.endsWith(".json")) {
					int id = Integer.parseInt((string.split("_")[1]).replace("V", "").replace(".json", ""));
					if (large < id) {
						large = id;
					}
				}
			}
			return large;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	@Override
	public DBFFileTrackDTO addRecordList(String fileName, List<HashMap<String, String>> recordsList) {
		DBFFileTrackDTO dto = new DBFFileTrackDTO();
		try {
//			HashMap<String, String> record = getPresentRecord(fileName,jsonRecord.get("DataNumber"));
			File file = new File(dtaxSharedFolder + "/" + fileName + "/" + fileName +"_V"+getVersion(fileName)+ ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));

				ObjectMapper mapper = new ObjectMapper();
				
				List<HashMap<String, String>> mapList = mapper.readValue(jsonArray.toString(),new TypeReference<List<HashMap<String, String>>>(){});
				
				if(mapList.size()!=jsonArray.size()) {
					return null;
				}

				for(int i=0;i<recordsList.size();i++) {
					if(recordsList.get(i).get("DataNumber")!=null) {
						int count = 0;
						for(int j=0;j<mapList.size();j++) {
							if(recordsList.get(i).get("DataNumber").equals(mapList.get(j).get("DataNumber"))) {
								updateRecord(fileName,mapList.get(j));
								count++;
							}
						}
						if(count==0) {
							mapList.add(recordsList.get(i));
							jsonArray.add(recordsList.get(i));
						}
					}
				}
				mapList.add(null);
				
				convertJSONFile(jsonArray, fileName, getLatestVersion(fileName));// write json
			}
			dto.setNewRecordsList(recordsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	public DBFFileTrackDTO updateRecord(String fileName, HashMap<String, String> mapRecord) {
		DBFFileTrackDTO dto = new DBFFileTrackDTO();
		try {
//			HashMap<String, String> record = getPresentRecord(fileName,jsonRecord.get("DataNumber"));
			File file = new File(dtaxSharedFolder + "/" + fileName + "/" + fileName +"_V"+getVersion(fileName)+ ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));

				ObjectMapper mapper = new ObjectMapper();
				
				List<HashMap<String, String>> mapList = mapper.readValue(jsonArray.toString(),new TypeReference<List<HashMap<String, String>>>(){});
				
				if(mapList.size()!=jsonArray.size()) {
					return null;
				}
				if(mapRecord.get("DataNumber") == null) {
					throw new Exception("DataNumber is null");  
				}
				for(int i=0;i<mapList.size();i++) {
					if(mapList.get(i).get("DataNumber").equals(mapRecord.get("DataNumber"))) {
						mapList.remove(i);
						jsonArray.remove(i);
						
						dto.setOldRecordsList(mapList);
						dto.setUpdatedRecord(mapRecord);
						
						mapList.add(i, mapRecord);
						jsonArray.add(i, mapRecord);
						break;
					}
				}
				
				convertJSONFile(jsonArray, fileName, getLatestVersion(fileName));// write json
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	private HashMap<String, String> getPresentRecord(String fileName, String recordId) {
		HashMap<String, String> record = new HashMap<String, String>();
		try {
			File file = new File(dtaxSharedFolder + "/" + fileName + "/" + fileName + "_V" + getVersion(fileName) + ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));

				ObjectMapper mapper = new ObjectMapper();

				List<HashMap<String, String>> mapList = mapper.readValue(jsonArray.toString(),new TypeReference<List<HashMap<String, String>>>() {});

				if (mapList.size() != jsonArray.size()) {
					return null;
				}
				for (int i = 0; i < mapList.size(); i++) {
				if (mapList.get(i).get("DataNumber") != null && mapList.get(i).get("DataNumber").toString().equals(recordId)) {
						record = mapList.get(i);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return record;
	}

	@Override
	public DBFFileTrackDTO updateStatus(String fileName, long recordId, boolean status) { // record is int
		DBFFileTrackDTO dto = new DBFFileTrackDTO();
		try {
			File file = new File(dtaxSharedFolder + "/" + fileName + "/" + fileName +"_V"+getVersion(fileName)+ ".json");
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
					if (mapList.get(i).get("DataNumber") != null && mapList.get(i).get("DataNumber").toString().equals(String.valueOf(recordId))) {
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
	
	private int getVersion(String fileName) {
		int version = getLatestVersion(fileName);
		if(version == 0) {
			version =1;
		}
		return version;
	}

	@Override
	public DBFFileTrackDTO deleteRecords(String fileName, List<String> recordsList){
		DBFFileTrackDTO dto = new DBFFileTrackDTO();
		try {
			File file = new File(dtaxSharedFolder + "/" + fileName + "/" + fileName +"_V"+getVersion(fileName)+ ".json");
			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));
				
				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> mapList = mapper.readValue(jsonArray.toString(),new TypeReference<List<HashMap<String, String>>>(){});
				
				if(mapList.size()!=jsonArray.size()) {
					return null;
				}
				List<HashMap<String, String>> deletedList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < recordsList.size(); i++) {
					for (int j = 0; j < mapList.size() && recordsList.get(i)!=null; j++) {
						if (mapList.get(j).get("DataNumber") != null && mapList.get(j).get("DataNumber").toString().equals(recordsList.get(i))) {
							mapList.get(j).replace("active", "false");
							
							jsonArray.remove(j);
							jsonArray.add(j, mapList.get(j));
							deletedList.add(mapList.get(j));
							break;
						}
					}
				}
				dto.setDeletedRecord(deletedList);
				convertJSONFile(jsonArray, fileName, getLatestVersion(fileName));// write json
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public DBFFileTrackDTO generateNewVersion(String fileName) {
		DBFFileTrackDTO dto = new DBFFileTrackDTO();
		try {
			JSONArray result = new JSONArray();
			File file = new File(dtaxSharedFolder + "/" + fileName + "/" + fileName +"_V"+getVersion(fileName)+ ".json");

			if (file.exists()) {
				JSONParser parser = new JSONParser();
				JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file.getAbsolutePath()));

				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					object.replace("testSession", "false");
					object.replace("ignore", "false");
					object.replace("active", "true");
					object.replace("isNew", "false");
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
}
