package com.wizeline.BO;

import com.wizeline.DTO.ResponseDTO;

public interface UserBO {

	ResponseDTO createUser(String user, String password);
	
	ResponseDTO login(String user, String password);
	
}
