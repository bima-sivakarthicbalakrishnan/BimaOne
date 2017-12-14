package com.bimaone.framework.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	public static Connection Dbcon = null;
	public static String URLDB = "jdbc:mysql://devdb.coic6imdwwxx.ap-south-1.rds.amazonaws.com:6603/dev_bima_mentor";
	public static String UN = "readonly";
	public static String Pwd = "readonly$123";

	public static ResultSet DBConnect(String Query) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Dbcon = DriverManager.getConnection(URLDB, UN, Pwd);
		Statement statement = Dbcon.createStatement();
		ResultSet rsObject = statement.executeQuery(Query);
		return rsObject;
	}

	public static ResultSet Connect(String Query) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Dbcon = DriverManager.getConnection(URLDB, UN, Pwd);
		Statement statement = Dbcon.createStatement();
		statement.executeUpdate(Query);
		return null;
	}
}
