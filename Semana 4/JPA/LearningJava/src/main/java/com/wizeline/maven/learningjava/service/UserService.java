package com.wizeline.maven.learningjava.service;

import com.wizeline.maven.learningjava.model.ResponseDTO;

public interface UserService {

	ResponseDTO createUser(String user, String password);
	
	ResponseDTO login(String user, String password);
	
}
