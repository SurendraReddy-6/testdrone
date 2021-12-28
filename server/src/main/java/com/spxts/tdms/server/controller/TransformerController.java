package com.spxts.tdms.server.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spxts.tdms.model.WaukeshaTransformer;
import com.spxts.tdms.server.enitity.TransformerDTO;
import com.spxts.tdms.server.service.TransformerService;

@RestController
public class TransformerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransformerService transformerService;

	@PermitAll
	@PostMapping("/transformer")
	public ResponseEntity<?> getTransformerDetails(@RequestBody TransformerDTO transformerDTO) {
		TransformerDTO response = new TransformerDTO();
		try {
			response = transformerService.getTransformerDetails(transformerDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@PermitAll
	@PostMapping("/api/datamodel")
	public ResponseEntity<?> getDataModel(@RequestBody TransformerDTO transformerDTO) throws FileNotFoundException, JAXBException {
		HashMap<String,WaukeshaTransformer> result = new HashMap<String,WaukeshaTransformer>();
		try {
			result = transformerService.getDataModel(transformerDTO);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
