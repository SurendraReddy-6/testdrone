package com.spxts.tdms.agent.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spxts.tdms.agent.enitity.Users;
import com.spxts.tdms.agent.repository.UsersRepository;
import com.spxts.tdms.agent.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public String authenticate(Users user) {
		logger.info("Begin: login()");
		Users response = new Users();
		String results = "";
		try {
			response = usersRepository.verifyLoginCredentails(user.getEmail(), user.getPassword());
			if(response.getId()>0) {
				results = "success";
			}else {
				results = "failed";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("End: login()");
		return results;
	}
}
