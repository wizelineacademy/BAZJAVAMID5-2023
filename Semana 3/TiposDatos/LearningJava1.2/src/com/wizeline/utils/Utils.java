/**
 * 
 */
package com.wizeline.utils;

/**
 * @author jonathan.torres
 *
 */
public class Utils {

	
	public static String getString(String value) {
		if(value != null) {
			return value;
		}
		return "";
	}
	
	public static boolean validateNullValue(String value) {
		if(value != null) {
			return true;
		}
		return false;
	}
}
