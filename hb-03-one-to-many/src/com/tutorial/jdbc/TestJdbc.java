package com.tutorial.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl  = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";

		try {
			System.out.println("connecting to db" + jdbcUrl );
			Connection myCon = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("connection successfull");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
