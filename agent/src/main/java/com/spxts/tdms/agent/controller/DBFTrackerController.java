package com.spxts.tdms.agent.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.websocket.server.PathParam;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spxts.tdms.agent.dto.DBFDTO;
import com.spxts.tdms.agent.service.DBFTrackerService;

@RestController
public class DBFTrackerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DBFTrackerService dbfTrackerService;
	
	@PermitAll
	@GetMapping("/api/dbf/list")
	public ResponseEntity<?> getHomePageList() {
		try {
			String[] response = dbfTrackerService.getHomePageList();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@PermitAll
	@GetMapping("/api/track/dbf/{fileName}")
	public ResponseEntity<?> trackDBF(@PathVariable String fileName) {
		DBFDTO results = new DBFDTO();
		try {
			results = dbfTrackerService.trackDBF(fileName);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/dbf/status/update")
	public ResponseEntity<?> updateStatus(@PathParam(value = "fileName") String fileName,@PathParam(value = "recordId") String recordId,@PathParam(value = "status") boolean status) {
		DBFDTO results = new DBFDTO();
		try {
			results = dbfTrackerService.updateStatus(fileName,recordId,status);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@PermitAll
	@DeleteMapping("/api/dbf/delete")
	public ResponseEntity<?> deleteRecord(@PathParam(value = "fileName") String fileName,@PathParam(value = "recordId") String recordId) {
		DBFDTO results = new DBFDTO();
		try {
			results = dbfTrackerService.deleteRecord(fileName,recordId);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@PermitAll
	@GetMapping("/api/generate/dbf/version")
	public ResponseEntity<?> generateNewVersion(@PathParam(value = "fileName") String fileName) {
		DBFDTO results = new DBFDTO();
		try {
			results = dbfTrackerService.generateNewVersion(fileName);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
