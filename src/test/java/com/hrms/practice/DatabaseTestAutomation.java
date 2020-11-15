package com.hrms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class DatabaseTestAutomation {
	
	//Selenium WebDriver--> Automating Browser--> GUI(Graphical user iNterface)
	//JDBC(Java DataBase Connectivity API) to execute queries in Selenium
	//What kind of databases we can access with jdbc--MYSQL, MS Access, SQL and etc.
	//SQL(structured query language) -- RDBMS-->Relational Database Management System
	//JDBC communicates with our java program
	
	//JDBC provides us; 
	//DriverManager class
	//Connection Interface
	//Statement Interface
	//ResultSet Interface
	//SQLException Class
	
	
	//Steps To achieve Database Testing by using Selenium
	
	//Preconditions;
	// We have to modify our POM.xml file.
	//we should include MYSQL JDBC maven repository
	
	//1.Create connection with the Database
	//2.Create Statement
	//3.Create Our Query
	//4.Proess Results
	//5.Close Connections
	
	/*
	 * We need info about our database:
	 * HostName: 166.62.36.207
	 * Port:3306
	 * DB Name:syntaxhrm_mysql
	 * UserName: syntax_hrm
	 * Password: syntaxhrm123
	 */
	
	public static void main(String[] args) {
		
	//1.Create connection with the Database
	//Syntax for establishing connection with db
	//DriverManager.getConnection(DatabaseURL, userId, password);
	//DatabaseURL Syntax
	//jdbc:jdbcType://ipAdress:portNumber/dbName
	//mysql - oracle and so on
	
	//String dbURL = "jdbc:mysql://166.62.36.207:3306/syntaxhrm_mysql"
	//            ip=HostName :postNumber
	
	String dbURL = "jdbc:mysql://166.62.36.207:3306/syntaxhrm_mysql";
	String userName = "syntax_hrm";
	String password = "syntaxhrm123";	
	
	
	//1.create connection with our database
	Connection connection = DriverManager.getConnection(dbURL, userName, password);
	
	//DatabaseMetaData --> Information about the database
	//-->Create an object of DatabaseMetaData
	DatabaseMetaData dbMetaData = connection.getMetaData();
	
	//By using dbMetaData object we will get some information
	// .getDriverName() -->returns the name of the JDBC driver
	
	String jdbcDriverName = dbMetaData.getDriverName();
	System.out.println("JDBC Driver Name: "+jdbcDriverName);
	
	//.getUserName()-->returns UserName();
	String username = dbMetaData.getUserName();
	System.out.println("Username :"+ username);
	
	//.getURL();
	//getDatabaseProductName() --> returns product name of the database
	
	//getDatabaseProductVersion() --> returns product version of the database
	

	
	//2. Create Statement
	Statement statement = connection.createStatement();
	
	System.out.println("Statement created");
	
	//3.Execute our query, so  we need ResultSet
	
	
	String query ="select * from ohrm_employment_status";
	
	ResultSet resultSet = statement.executeQuery(query);
	
	//ResultSetMetaData --> information about the resultSet
	//Create an object of ResultSetMetaData by using resultSet object
	
	ResultSetMetaData rsMetaData =resultSet.getMetaData();
	
	//getColumnCount() --> returns number of coloumns in resultSet
	int colCount = rsMetaData.getColumnCount();
	System.out.println("Number of coloumns in resultSet : "+ colCount);
	
	
	// getColumnName(int index) --> returs name of column at gieven index
	String colname1 = rsMetaData.getColumnName(1);  //cols index starts from 1, if we start from 0 gives us SQLEXCEption
	System.out.println("Name of first coloumn : "+ colname1);
	
	// getColumnTypeName(int index)--> Returns type of column at given index
	System.out.println("Column Type : "+ rsMetaData.getColumnTypeName(1)); //Column Type: int
	
	
	// getTableName();
	
	
	//4. Process Results
	//Lets print information we get from the query
	//next() --> moves the cursor to the one more row next from the current position
//	
//	resultSet.next(); // -->the cursor came to the first row
//	String id = resultSet.getString(1);  //cursor i coloumn 1 e getirir
//	System.out.println(id);
//	
//	String empStatus = resultSet.getString("name");//getString(ColumnLabel)
//	System.out.println(id+ "-->"+ empStatus);
//	
//
//	resultSet.next(); // -->the cursor came to the first row
//    id = resultSet.getString(1);  //cursor i coloumn 1 e getirir
//    empStatus = resultSet.getString(id+"-->"+ "name");
	

	while (resultSet.next()) {
		String id = resultSet.getString(1);  //cursor i coloumn 1 e getirir. Returns the value of  given cell index
		String empStatus = resultSet.getString("name");
		System.out.println(id+"-->"+ empStatus);
	}
	
		
		//5. Close Connections
	// connection -statement -resultset
	
	resultSet.close();
	statement.close();
	connection.close();	
	}
	
	//MetaData--> Data is about Data
	//1.DatabaseMetaData --> information about the database (we can get the server, username etc.)
	//2.ResultSetMetaData --> Information about the resultSet (How many cols, etc we can get)
	
	
	}

