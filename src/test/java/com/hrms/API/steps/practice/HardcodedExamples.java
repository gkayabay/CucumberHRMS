package com.hrms.API.steps.practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/**
 * Rest assured static packages
 */
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //manually we have to import this to use equalTo method
/**
 * Importing JUnit packages
 */
import org.junit.Assert;
import org.junit.Test;

//please import
import org.junit.*;
import org.junit.runners.MethodSorters;
/**
 *We may use below-please comment out for now
 * @author gulbahar
 *
 */
//import org.apache.hc.core5.http.ContentType;

import com.hrms.API.utils.PayloadConstants;

/**
 * This @FixMethodOrder(MethodSorters.NAME_ASCENDING) will execute @Test
 * annotation inascending alphabetical order (@FixMethodOrder is calismasi icin
 * import org.junit.runners.MethodSorters above
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardcodedExamples {
	/**
	 * Rest Assured
	 *
	 * given - prepare your request when - you are making the call to the endpoint
	 * then - validating
	 *
	 * @param args
	 */

	static String baseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU2ODkzMTQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTczMjUxNCwidXNlcklkIjoiNTA1In0.4GnrVZIwxJ32MMSQJKapIvgF2kJlJgsYlL-i1QhBEj";
	public static String employeeID;

	@Test
	public void sampleTestNotes() {
		/**
		 * BaseURI for all calls
		 */
		RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
		/**
		 * JWT required for all calls - expires every 12 hours
		 */
		String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUyMDY0MDksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTI0OTYwOSwidXNlcklkIjoiNTA1In0.zh6IwZZYAkpogfe348x4MSA-ol-C6tWrxPTr9xk2Aw0";
		/**
		 * Preparing /getOneEmployee.php request
		 */
		RequestSpecification getOneEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", "19216A"); // log().all();
		/**
		 * Storing response
		 */
		Response getOneEmployeeResponse = getOneEmployeeRequest.when().get("/getOneEmployee.php");
		/**
		 * Two ways to print response prettyPrint() method converts JSON object into
		 * String and prints - no need to SOP
		 */
		getOneEmployeeResponse.prettyPrint();
		// String response = getOneEmployeeResponse.body().asString();
		// System.out.println(response);
		/**
		 * Verifying response status code is 200
		 */
		getOneEmployeeResponse.then().assertThat().statusCode(200);
	}

	@Test
	public void aPOSTcreateEmployee() {
		/**
		 * Preparing request for /createEmployee.php
		 */
		RequestSpecification createEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\n" + "  \"emp_firstname\": \"Anthonyww\",\n" + "  \"emp_lastname\": \"Ant\",\n"
						+ "  \"emp_middle_name\": \"A\",\n" + "  \"emp_gender\": \"M\",\n"
						+ "  \"emp_birthday\": \"2020-07-18\",\n" + "  \"emp_status\": \"Employee\",\n"
						+ "  \"emp_job_title\": \"Cloud Architect\"\n" + "}"); // .log().all();
		/**
		 * Storing response into createEmployeeResponse
		 */
		Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php"); // we can include
																									// log().all() after
																									// when also to
																									// print details
		/**
		 * Printing response using prettyPrint() method
		 */
		createEmployeeResponse.prettyPrint();
		/**
		 * jsonPath() to view response body which lets us get the employee ID We store
		 * employee ID as a global variable so that we may we use it with our other
		 * calls
		 *
		 */
		employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
		/** optional to print employee ID */
		System.out.println(employeeID);
		/**
		 * Verifying response status code is 201
		 */
		createEmployeeResponse.then().assertThat().statusCode(201);
		/**
		 * Verifying message using equalTo() method - manually import static package
		 * import static org.hamcrest.Matchers.*;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));
		/**
		 * Verifying created first name
		 */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("Anthonyww"));
		/**
		 * Verifying server using then().header()
		 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
		/**
		 * Verifying Content-Type using assertThat().header()
		 */
		createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");
	}

	@Test
	public void bGETcreatedEmployee() {

		/**
		 * Preparing request for /getOneEmployee.php Using log().all() to see all
		 * information being sent with request
		 */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID).log().all();
		/**
		 * Making call to retrieve created employee
		 */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");
		/**
		 * Printing response using prettyPrint()
		 */
		String response = getCreatedEmployeeResponse.prettyPrint();
		/**
		 * Storing response employeeID into empID which will be used for verification
		 * against stored global employee ID
		 */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		/**
		 * matching exact employeeID’s
		 */
		boolean verifyingEmpoyeeIDsMatch = empID.contentEquals(employeeID);
		System.out.println("Employee ID’s match: " + verifyingEmpoyeeIDsMatch);
		/**
		 * Asserting employee ID’s match
		 */
		Assert.assertTrue(verifyingEmpoyeeIDsMatch);

		/**
		 * Verifying status code is 200
		 */
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);

		// it goes to the obj and retrieves any values response--> came from
		// prettyPrint.
		// JsonPath class expects a String
		/**
		 * Using JsonPath class retrieve values from response as a String
		 * 
		 */

		JsonPath js = new JsonPath(response);
		String emplID = js.getString("employee[0].employee_id");
		String firstName = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastName = js.getString("employee[0].emp_lastname");
		String emp_bday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String empStatus = js.getString("employee[0].emp_status");

		System.out.println(empID);
		System.out.println(firstName);

		/** Verifying employee ID`s match */
		Assert.assertTrue(empID.contentEquals(employeeID)); // I want to get exact match so I used ContentEquals
		// second way of asserting
		Assert.assertEquals(employeeID, emplID); // expected-->emloyeeID , actual -->empID

		/** Verifying expected firstname mattches actual first name */
		Assert.assertTrue(firstName.contentEquals("Anthonyww"));

		/** Verifying expected middle name mattches actual middle name */
		Assert.assertTrue(middleName.contentEquals("A"));

		/** Verifying expected last name matches actual last name */
		Assert.assertTrue(lastName.contentEquals("Ant"));

		/** Verifying expected birth date matches actual birth date */
		Assert.assertTrue(emp_bday.contentEquals("2020-07-18"));

		/** Verifying expected gender matches actual gender */
		Assert.assertTrue(gender.contentEquals("Male"));

		/** Verifying expected job title matches actual job title */
		Assert.assertTrue(jobTitle.contentEquals("Cloud Architect"));

		/** Verifying expected employee status matches actual employee status */
		Assert.assertTrue(empStatus.contentEquals("Employee"));

	}

	@Test
	public void cGETallEmployees() {

		/**
		 * Preparing /getAllEmployees.php request
		 */
		RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token);
		Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

		/**
		 * Printing all employees
		 */
		// getAllEmployeesResponse.prettyPrint();

		String allEmployees = getAllEmployeesResponse.body().asString();

		// allEmployees.contains(employeeID); //this method will pass but incorrect

		JsonPath js = new JsonPath(allEmployees);

		/** Retrieving size of Employee List */

		int sizeOfList = js.getInt("Employees.size()"); // it goes to the Employees arraylist( size() methodu
														// kullanilir) and gets the size as int

		System.out.println(sizeOfList);

		/** Print out all employee ID`s */

//    for(int i = 0; i< sizeOfList; i++) {  //loop through the all employees
//    	
//    	/**
//    	 * Printing all employee IDs
//    	 */
//    	String allEmployeeIDs = js.getString("Employees["+ i + "].employee_id");
//    	//System.out.println(allEmployeeIDs);
//    	
//    	/**
//    	 * if statement inside for loop to find storedd employee ID and break the loop
//    	 * when found
//    	 */
//    	if(allEmployeeIDs.contentEquals(employeeID)) {
//    		System.out.println("Employee ID: "+ employeeID + " is present in body");
//    		String employeeFirstName = js.getString("Employees["+ i + "].emp_firstname");
//    		System.out.println(employeeFirstName);
//    		
//    		break;
//    	}
//    }
//    

	}

	@Test
	public void dPUTupdateCreatedEmployee() {



		RequestSpecification updateCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body(PayloadConstants.updateCreatedEmpBody()); //updateCreatedEmpBody came from HardcodedConstatnts class. Here we made the emp body and stored in a string variable

		/**
		 * Storing response into updateCreatedEmployeeResponse
		 * 
		 */
		Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");

		/**
		 * Storing response into aString
		 * 
		 */
		
		//String response = updateCreatedEmployeeResponse.prettyPrint();
		
		/**
		 * Asserting using hamcrest matchers equalTo() method to verify employee is updated
		 * 
		 */
		
		
		updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));
	
		
		/**
		 * Retrieving response body employee ID using jsonPath
		 * 
		 */
		
		String empID = updateCreatedEmployeeResponse.body().jsonPath().getString("employee[0],employee_id");		
	 	
		/**
		 * Asserting that response body employee ID matches globally stored employeeID
		 * 
		 */
		Assert.assertTrue(empID.contentEquals(employeeID));
	}
	
	@Test
	public void eGETUpdatedEmployee() {
		/**
		 * Preparing request to get an employee
		 */
		
		RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type","application/json").header("Authorization", token).queryParam("employee_id",employeeID);
		
		/**
		 * Storing response into getUpdatedEmployeeResponse
		 * 
		 */
		Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");
	
		//Printing response
		//getUpdatedEmployeeResponse.prettyPrint();
		
		
		/**
		 * Asserting expected first name
		 * 
		 */
		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_firstname", equalTo("Nisa"));
		
		/**
		 * Verifying response employee ID is equal to globally stored employee ID
		 */
		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].employee_id", equalTo(employeeID));
		
	}
	
	public void fPATCHpartiallyUdateEmployee() {
		RequestSpecification partiallyUpdatingEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" + 
				"  \"employee_id\": \""+employeeID+"\",\n" + 
				"  \"emp_firstname\": \"ZenziUpdatedFirstNameAgain\" \n" + 
				"}");
		
		
	Response partiallyUpdatingEmployeeResponse = partiallyUpdatingEmployeeRequest.when().patch("/updatePartialEmplyeesDetails.php");
	
	//partiallyUpdatingEmployeeResponse.prettyPrint();
		
	partiallyUpdatingEmployeeResponse.then().assertThat().statusCode(201);
	
	//verifying response body Message -->Entry created
	partiallyUpdatingEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));
	
	}
	@Test
	public void gDELETEemployee() {
	
	RequestSpecification deleteEmployeeRequest=	given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employeeID);
		
	Response deleteEmployeeResponse = deleteEmployeeRequest.when().delete("/deleteEmployee.php");
		
	deleteEmployeeResponse.prettyPrint();
	
	deleteEmployeeResponse.then().assertThat().body("message", equalTo("Entry deleted"));
	
	}
	
	}
