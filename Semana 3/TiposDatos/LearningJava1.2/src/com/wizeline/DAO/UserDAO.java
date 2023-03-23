/**
 * 
 */
package com.wizeline.DAO;

/**
 * @author Wizeline
 *
 */
public interface UserDAO {

	String createUser(String user, String password);
	
	String login(String user, String password);
	
}
