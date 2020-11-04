package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;


@SpringBootApplication
public class TestApplication {
	
	// JDBC driver name and database URL
	   //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/users_database";

	   //  Database credentials
	   static final String USER = "admin";
	   static final String PASS = "Vidula@9975";
	   
	public static void main(String[] args) {		   
	
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      //Class.forName("com.mysql.jdbc.Driver");
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");

		      String sql = "SELECT * FROM roles";
		      stmt = conn.createStatement();
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         String name= rs.getString("name");
		         
				         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", name: " + name);

		      }
		      rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   
		   
		   
	}

}
