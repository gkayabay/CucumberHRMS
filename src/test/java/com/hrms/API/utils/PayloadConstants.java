package com.hrms.API.utils;

import org.json.JSONObject;

import com.hrms.API.steps.practice.HardcodedExamples;

public class PayloadConstants {
	
	
	
	/**
	 * Create employee body
	 * @return
	 */
	 
	public static String createEmployeeBody() {
		String createEmployeeBody = "{\n" + 
				"  \"emp_firstname\": \"Anthonyww\",\n" + 
				"  \"emp_lastname\": \"Ant\",\n" + 
				"  \"emp_middle_name\": \"A\",\n" + 
				"  \"emp_gender\": \"M\",\n" + 
				"  \"emp_birthday\": \"2020-07-18\",\n" + 
				"  \"emp_status\": \"Employee\",\n" + 
				"  \"emp_job_title\": \"Cloud Architect\"\n" + 
				"}";
					
	 return createEmployeeBody;
	}
	
	/**
	 * 
	 * Creating payload using JSONObject and returning as a String
	 * @return
	 */
	
	public static String createEmployeePayload() {
		
		//to create an obj during runtime we can retrieve the obj
		//create json obj
		//before we paassed it we have to serilize
		JSONObject obj = new JSONObject() ;
		
		obj.put("emp_firstname", "Anthonyww");  //put String(key, value)
		obj.put("emp_lastname", "Ant"); 
		obj.put("emp_middle_name", "A"); 
		obj.put("emp_gender", "M"); 
		obj.put("emp_birthday", "2020-07-18"); 
		obj.put("emp_status", "Employee"); 
		obj.put("emp_job_title", "Cloud Architect"); 
		
		return obj.toString();
	
	}
	
	/**
	 * 
	 * Created method to return payload - to reduce messy code
	 * @return
	 */
	
	public static String updateCreatedEmpBody() {
		String updateBody = "{\n" + "  \"employee_id\": \"" + HardcodedExamples.employeeID + "\",\n" + "  \"emp_firstname\": \"Nisa\",\n"
				+ "  \"emp_lastname\": \"Noraaa\",\n" + "  \"emp_middle_name\": \"N\",\n" + "  \"emp_gender\": \"F\",\n"
				+ "  \"emp_birthday\": \"2020-07-11\",\n" + "  \"emp_status\": \"Employee\",\n"
				+ "  \"emp_job_title\": \"IT Support Manager\"\n" + "} ";

		return updateBody;
	}

}
