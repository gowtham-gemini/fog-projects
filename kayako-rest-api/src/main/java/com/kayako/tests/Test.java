package com.kayako.tests;


import com.kayako.api.configuration.Configuration;
import com.kayako.api.test.TestAPI;

public class Test {
	/**
	 * Please change the following Configuration Keys and Details according to your help desk.
	 */
	public static final String API_URL = "http://fptest.kayako.com/api/";
	
	public static final String API_KEY = "6fa09bba-7286-f7d4-9d89-b780bdb9f4bb";
	
	public static final String SECRET_KEY = "NDE0OWEwNzAtYjUyYy03M2Y0LTUxNGEtNjY2YjMwNThkZTdmN2JkN2Q3OGYtY2JkYi0xYjQ0LWQ1NzctYjgwNDEyOWUyZGMx";
	
	
	public static Configuration GetConfigurations() {
		return Configuration.init(API_URL, API_KEY, SECRET_KEY, true);
	}
	
	public static void main(String[] args) {
		
		Configuration.setConfiguration(GetConfigurations());
		
		try {
			TestAPI.Check();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}