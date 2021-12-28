package com.spxts.tdms.server.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.spxts.tdms.dobleUtils.DataModelR2;
import com.spxts.tdms.server.ImpulseModel.ImpulseSurgeLogDTO;
import com.spxts.tdms.server.ImpulseModel.TestSurgeJson;
import com.spxts.tdms.server.service.DBTAndDXMReadService;

@Service
public class DBTAndDMXMFileReadServiceImpl implements DBTAndDXMReadService {

	@Value("${source.IMPULSE_JSON_SHARED_FOLDER}")
	private String impulseJSONSharedFolder;
	
	@Value("${property.ImpulseFileExtension}")
	private String impulseJsonExtension;
	
	@Override
	public Map<Long, List<Object>> uploadDBTData(MultipartFile file) {
		HashMap<Long, List<Object>> result = new HashMap<Long, List<Object>>();
		try {
			StringBuilder builder = new StringBuilder();

			DBFReader reader = new DBFReader(file.getInputStream());
			int numberOfFields = reader.getFieldCount();

			List<Object> headers = new ArrayList<Object>();
			for (int i = 0; i < numberOfFields; i++) {
				DBFField field = reader.getField(i);
				headers.add(field.getName());
//				System.out.print(field.getName()+" || ");
				if (i == numberOfFields - 1) {
					builder.append(field.getName());
				} else {
					builder.append(field.getName() + ",");
				}
			}
			builder.append('\n');

			result.put(0L, headers);
			Object[] rowObjects;
			Long count = 1L;
			while ((rowObjects = reader.nextRecord()) != null) {
				List<Object> singleRow = new ArrayList<Object>();
				String[] data = new String[rowObjects.length];

				for (int i = 0; i < rowObjects.length; i++) {
//					System.out.print(rowObjects[i] + " || ");
					singleRow.add(rowObjects[i]);
					if (rowObjects[i] == null) {
						data[i] = "null";
						if (i == numberOfFields - 1) {
							builder.append("null");
						} else {
							builder.append("null" + ",");
						}
					} else {
						data[i] = rowObjects[i].toString();
						if (i == numberOfFields - 1) {
							builder.append(rowObjects[i].toString());
						} else {
							builder.append(rowObjects[i].toString() + ",");
						}
					}
				}
				builder.append('\n');
				result.put(count, singleRow);
				count++;
			}
			generateCSVFile(builder.toString(),file.getOriginalFilename());

			System.out.println(reader.getFieldCount() + " === " + reader.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void generateCSVFile(String resultString, String fileName) throws IOException {
		fileName = fileName.replace(".dbf",".csv");
		//PrintWriter pw = new PrintWriter(new File("C:\\Users\\Pushpa Kumar\\Desktop\\"+fileName));
		PrintWriter pw = new PrintWriter(new File("C:\\Work\\Working\\Tranformers\\OutputFiles\\"+fileName));
		pw.write(resultString);
		pw.close();

	}

	@Override
	public String createDBTJSON(ImpulseSurgeLogDTO impulseSurgelog) {
		// TODO Auto-generated method stub
		//ImpulseSurgeLogDTO impulseSurgelog = new ImpulseSurgeLogDTO();
		String iSurgeJson = convertObjectToJson(impulseSurgelog);
		System.out.println("Test"+iSurgeJson);
		return iSurgeJson;
	}
	
	public String convertObjectToJson(ImpulseSurgeLogDTO dataModel) {
	GsonBuilder gsonBuilder = new GsonBuilder();
	gsonBuilder.setPrettyPrinting();
	gsonBuilder.disableHtmlEscaping();
	Gson gson = gsonBuilder.create();	
	return gson.toJson(dataModel,ImpulseSurgeLogDTO.class);
		
	}
	

	public Map<Long, List<Object>> uploadDBTDataFile(File file) {
		HashMap<Long, List<Object>> result = new HashMap<Long, List<Object>>();
		try {
			StringBuilder builder = new StringBuilder();
			FileInputStream inStream = new FileInputStream(file);
			DBFReader reader = new DBFReader(inStream);
			int numberOfFields = reader.getFieldCount();

			List<Object> headers = new ArrayList<Object>();
			for (int i = 0; i < numberOfFields; i++) {
				DBFField field = reader.getField(i);
				headers.add(field.getName());
//				System.out.print(field.getName()+" || ");
				if (i == numberOfFields - 1) {
					builder.append(field.getName());
				} else {
					builder.append(field.getName() + ",");
				}
			}
			builder.append('\n');

			result.put(0L, headers);
			Object[] rowObjects;
			Long count = 1L;
			while ((rowObjects = reader.nextRecord()) != null) {
				List<Object> singleRow = new ArrayList<Object>();
				String[] data = new String[rowObjects.length];

				for (int i = 0; i < rowObjects.length; i++) {
//					System.out.print(rowObjects[i] + " || ");
					singleRow.add(rowObjects[i]);
					if (rowObjects[i] == null) {
						data[i] = "null";
						if (i == numberOfFields - 1) {
							builder.append("null");
						} else {
							builder.append("null" + ",");
						}
					} else {
						data[i] = rowObjects[i].toString();
						if (i == numberOfFields - 1) {
							builder.append(rowObjects[i].toString());
						} else {
							builder.append(rowObjects[i].toString() + ",");
						}
					}
				}
				builder.append('\n');
				result.put(count, singleRow);
				count++;
			}
			generateCSVFile(builder.toString(),file.getName());

			System.out.println(reader.getFieldCount() + " === " + reader.getRecordCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	@Override
	public String getDBTJSON(String transformerId) throws IOException {
		// TODO Auto-generated method stub
		if(impulseJSONSharedFolder==null||impulseJsonExtension==null) {
			impulseJSONSharedFolder="src/main/resources/IMPULSE_JSON_FOLDER";
			impulseJsonExtension = "_ImpulseSurgeLog.json";
		}
		File folder = new File(impulseJSONSharedFolder+File.separator+transformerId);
		File file = new File(folder.getPath()+File.separator+transformerId+impulseJsonExtension);
		System.err.println(file);
		if(file.exists()) {
			ImpulseSurgeLogDTO impulseJsonDTO = getObjectFromJson(file);
			String impulseJson = convertObjectToJson(impulseJsonDTO);	
			return impulseJson;
		}
		else {
			
			File jsonTemplate = new File(impulseJSONSharedFolder+File.separator+"Template_ImpulseSurgeLog.json");
			ImpulseSurgeLogDTO newImpulseJsonObject=getObjectFromJson(jsonTemplate);
			newImpulseJsonObject.setUnit(transformerId);
			String impulseJson = jsonBuilder(newImpulseJsonObject,transformerId);
			return impulseJson;
		}
		
	}
	
	public String jsonBuilder(ImpulseSurgeLogDTO jsonObject,String transformerId) throws IOException {
		File folder = new File(impulseJSONSharedFolder+File.separator+transformerId);
		if(!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(folder.getPath()+File.separator+transformerId+impulseJsonExtension);
		file.createNewFile();
		//FileReader jsonReader = new FileReader(file);
		GsonBuilder gbuilder = new GsonBuilder();
		gbuilder.setPrettyPrinting();
		Gson json = gbuilder.create();
		String jsonFile=json.toJson(jsonObject, ImpulseSurgeLogDTO.class);
		FileWriter jsonFileWriter = new FileWriter(file);
		jsonFileWriter.write(jsonFile);
		jsonFileWriter.flush();
		jsonFileWriter.close();
		return jsonFile;
		
	}
	
public ImpulseSurgeLogDTO getObjectFromJson(File jsonFile) throws FileNotFoundException {
	System.out.println(jsonFile.getPath());
	ImpulseSurgeLogDTO jsonObject;
	Gson json = new Gson();
	FileReader fr = new FileReader(jsonFile);
	jsonObject = json.fromJson(fr,ImpulseSurgeLogDTO.class);
	//jsonObject= json.fromJson(jsonFileSring, ImpulseSurgeLogDTO.class);
	return jsonObject;
}

public ImpulseSurgeLogDTO getObjectFromJson(File jsonFile,String transformerId) throws FileNotFoundException {
	ImpulseSurgeLogDTO jsonObject;
	Gson json = new Gson();
	FileReader fr = new FileReader(jsonFile);
	String jsonFileSring=fr.toString();
	jsonObject= json.fromJson(jsonFileSring, ImpulseSurgeLogDTO.class);
	return jsonObject;
}


}