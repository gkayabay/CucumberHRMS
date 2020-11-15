package com.hrms.API.steps.practice;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class TokenGenerationSteps {
	
	//make token global variable
	/**
	 * Storing token as static variable
	 * 
	 */
	public static String token;
	
	/**
	 * BaseURI
	 * 
	 */
	String baseURI = RestAssured.baseURI= "http://18.232.148.34/syntaxapi/api";
	
	
	@Given("a JWT is generated")
	public void a_JWT_is_generated() {
		
	RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json").body("{\n" + 
				"  \"email\": \"kayabay319@gmail.com\",\n" + 
				"  \"password\": \"syntax2020\"\n" + 
				"}");
	   
	Response generateTokenResponse = generateTokenRequest.when().post("/generateToken.php");
		
	//generateTokenResponse.prettyPrint();	
	
	/**
	 * Retrieving token from generate response body and concatinating "Bearer"
	 * 
	 */
	
	token = "Bearer " + generateTokenResponse.body().jsonPath().getString("token");
	
	
	/**
	 * Optional to print out token
	 */
	System.out.println(token);
	
	}
}
