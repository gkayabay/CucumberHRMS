package com.hrms.steps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class DbStep {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	@Given("I create connection with SyntaxHrms database")
	public void i_create_connection_with_SyntaxHrms_database() {
	 

		String dbURL = "jdbc:mysql://166.62.36.207:3306/syntaxhrm_mysql";
		String userName = "syntax_hrm";
		String password = "syntaxhrm123";	
		
		try {
			connection =DriverManager.getConnection(dbURL, userName, password);
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Given("I create a statement")
	public void i_create_a_statement() {
	    
	}

	@When("I retrieve all job tittles from database")
	public void i_retrieve_all_job_tittles_from_database() {

		String query ="select * from ohrm_employment_status";
		
		ResultSet resultSet = statement.executeQuery(query);
	}

	@Then("I validate results for Job Titles")
	public void i_validate_results_for_Job_Titles(io.cucumber.datatable.DataTable dataTable) {
	    
		//we will compare results in resultSet and in dataTable
		
		List<Map<String, String>> expResults = dataTable.asMaps();
		
		//loop through inside our datatable
		Iterator<Map<String, String>> it = expResults.iterator();
		
		resultSet.next();
		
		
		while (it.hasNext()) {
			
			//retrieve expected value from datatable
			Map<String, String> expRes = it.next();
			String expJobTitle = expRes.get("job_title"); //expecRes comes from sql database
		
		
		//retrieve actual result from resultSet
		
		String actJobTitle  = resultSet.getString("job_title"); //after getting the first value 
		resultSet.next(); //we move to the next jobtitle
		
		Assert.assertEquals(expJobTitle,  actJobTitle);
		
		}
		
		
	}

	@Then("I close all connections")
	public void i_close_all_connections() {
		

		resultSet.close();
		statement.close();
		connection.close();	
	    
	}



	
	
	
	
	
	
	
	
	
	
	
	
	

}
