/**
 * 
 */
package com.wizeline.maven.learningjava.model;

import java.util.Map;

/**
 * @author jonathan.torres
 *
 */
public class UserDTO {

	private String user;
	
	private String password;

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public UserDTO getParameters(Map<String, String> userParam) {
		UserDTO user = new UserDTO();
		user.setUser(userParam.get("user"));
		user.setPassword(userParam.get("password"));
		return user;
	}
}
