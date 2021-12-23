package com.spxts.tdms.server.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.websocket.server.PathParam;
import javax.xml.bind.JAXBException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.spxts.tdms.server.dto.DBFFileTrackDTO;
import com.spxts.tdms.server.service.WaukeshaTransformerService;

@Controller
public class WaukeshaTransformerController {

	@Autowired 
	private WaukeshaTransformerService waukeshaTransformerService;
	
	@PermitAll
	@GetMapping("/api/homepage/list")
	public ResponseEntity<?> getHomePageList() throws FileNotFoundException, JAXBException {
		try {
			String[] result = waukeshaTransformerService.getHomePageList();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/track/dbf/{fileName}")
	public ResponseEntity<?> trackDBF(@PathVariable String fileName) {
		DBFFileTrackDTO results = new DBFFileTrackDTO();
		try {
			results = waukeshaTransformerService.trackDBF(fileName);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/dbf/add-record")
	public ResponseEntity<?> addRecordList(@PathParam(value = "fileName") String fileName, @RequestBody List<HashMap<String, String>> record) {
		DBFFileTrackDTO results = new DBFFileTrackDTO();
		try {
			results = waukeshaTransformerService.addRecordList(fileName,record);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/dbf/update-record")
	public ResponseEntity<?> updateRecord(@PathParam(value = "fileName") String fileName, @RequestBody HashMap<String, String> record) {
		DBFFileTrackDTO results = new DBFFileTrackDTO();
		try {
			results = waukeshaTransformerService.updateRecord(fileName,record);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/dbf/status/update")
	public ResponseEntity<?> updateStatus(@PathParam(value = "fileName") String fileName,@PathParam(value = "recordId") long recordId,@PathParam(value = "status") boolean status) {
		DBFFileTrackDTO results = new DBFFileTrackDTO();
		try {
			results = waukeshaTransformerService.updateStatus(fileName,recordId,status);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PermitAll
	@DeleteMapping("/api/dbf/delete")
	public ResponseEntity<?> deleteRecords(@PathParam(value = "fileName") String fileName,@RequestBody List<String> recordsList) {
		DBFFileTrackDTO results = new DBFFileTrackDTO();
		try {
			results = waukeshaTransformerService.deleteRecords(fileName,recordsList);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/generate/dbf/version")
	public ResponseEntity<?> generateNewVersion(@PathParam(value = "fileName") String fileName) {
		DBFFileTrackDTO results = new DBFFileTrackDTO();
		try {
			results = waukeshaTransformerService.generateNewVersion(fileName);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
