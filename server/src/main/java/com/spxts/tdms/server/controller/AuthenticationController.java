package com.spxts.tdms.server.controller;

import javax.annotation.security.PermitAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spxts.tdms.server.enitity.Users;
import com.spxts.tdms.server.service.AuthenticationService;

@RestController
public class AuthenticationController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthenticationService authenticationService;

	@PermitAll
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users users) {
		String response = "";
		try {
			response = authenticationService.authenticate(users);
			if (response.equals("success")) {

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
