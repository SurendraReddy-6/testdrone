package com.spxts.tdms.server.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spxts.tdms.server.ImpulseModel.ImpulseSurgeLogDTO;
import com.spxts.tdms.server.service.DBTAndDXMReadService;

@RestController
public class DBTAndDXMFileReadController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DBTAndDXMReadService dbtAndDXMReadService;

	
	@RequestMapping(value = "/api/dbt/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<?> uploadDBTData(@RequestPart("file") MultipartFile file){
		Map<Long, List<Object>> responseData = new HashMap<Long, List<Object>>();
		responseData = dbtAndDXMReadService.uploadDBTData(file);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	
	///api/dbt/fetchJsonData
	
	@RequestMapping(value = "/api/dbt/getimpulsejson", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public  ResponseEntity<?> getDBTJsonData(@RequestParam String transformerId) throws IOException{
		//Map<Long, List<Object>> responseData = new HashMap<Long, List<Object>>();
		System.out.println("get json request :"+transformerId);
		String responseData = dbtAndDXMReadService.getDBTJSON(transformerId);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/dbt/impulsejsonsubmit", method = RequestMethod.POST, consumes = { "application/json" }, produces = {"application/json"})
	@ResponseBody
	public  ResponseEntity<?> submitDBTJsonData(@RequestBody ImpulseSurgeLogDTO impulseSurgeLogDTO){
		//Map<Long, List<Object>> responseData = new HashMap<Long, List<Object>>();
		System.out.println("json submit request");
		String responseData = dbtAndDXMReadService.createDBTJSON(impulseSurgeLogDTO);
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
}
