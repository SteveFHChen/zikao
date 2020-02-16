package com.dao;

public abstract class CommonDao {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	protected static String url = "jdbc:mysql://localhost:3306/myweb1?characterEncoding=utf8&serverTimezone=UTC";
	protected static String userName = "root";
	protected static String password = "root123";
	*/
	
//	protected static String url = "jdbc:mysql://120.24.55.104:3306/stevemyweb1?characterEncoding=utf8&serverTimezone=UTC";
	protected static String url = "jdbc:mysql://localhost:3306/stevemyweb1?characterEncoding=utf8&serverTimezone=UTC";
	protected static String userName = "steve";
	protected static String password = "Steve123!";
	
}
