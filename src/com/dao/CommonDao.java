package com.dao;

import com.listener.SystemListener;

public abstract class CommonDao {

	static {
		try {
			Class.forName(SystemListener.get("db.driver"));
			System.out.println(CommonDao.class.getName() + " static executed.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Local test
	//protected static String url = "jdbc:mysql://localhost:3306/myweb1?characterEncoding=utf8&serverTimezone=UTC";
	//protected static String userName = "root";
	//protected static String password = "abcd";
	protected static String url = SystemListener.get("db.connectionString");
	protected static String userName = SystemListener.get("db.username");
	protected static String password = SystemListener.get("db.password");
	
}
