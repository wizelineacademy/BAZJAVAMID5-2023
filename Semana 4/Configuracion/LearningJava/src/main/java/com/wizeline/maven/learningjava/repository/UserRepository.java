/**
 * 
 */
package com.wizeline.maven.learningjava.repository;

/**
 * @author Wizeline
 *
 */
public interface UserRepository {

	String createUser(String user, String password);
	
	String login(String user, String password);
	
}
