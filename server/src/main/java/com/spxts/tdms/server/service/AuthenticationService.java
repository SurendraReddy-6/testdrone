package com.spxts.tdms.server.service;

import com.spxts.tdms.server.enitity.Users;

public interface AuthenticationService {

	public String authenticate(Users user);

}
