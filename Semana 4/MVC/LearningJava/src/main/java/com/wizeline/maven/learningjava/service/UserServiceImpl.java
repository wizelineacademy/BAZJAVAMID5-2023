/**
 * 
 */
package com.wizeline.maven.learningjava.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.wizeline.maven.learningjava.model.ErrorDTO;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.repository.UserRepository;
import com.wizeline.maven.learningjava.repository.UserRepositoryImpl;
import com.wizeline.maven.learningjava.utils.Utils;

/**
 * @author jonathan.torres
 *
 */

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Override
	public ResponseDTO createUser(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "fail"; 
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepository userDao = new UserRepositoryImpl();
			result = userDao.createUser(user, password);
			response.setCode("OK000");
			response.setStatus(result);
		}else {
			response.setCode("OK000");
			response.setStatus(result);
			response.setErrors(new ErrorDTO("ER001","Error al crear usuario"));
		}
		return response;
	}

	@Override
	public ResponseDTO login(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepository userDao = new UserRepositoryImpl();
			result = userDao.login(user, password);
		}
		if("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER001");
			response.setErrors(new ErrorDTO("ER001",result));
			response.setStatus("fail");
		}
		return response;
	}

}
