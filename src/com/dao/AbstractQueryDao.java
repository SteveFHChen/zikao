package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractQueryDao extends CommonDao implements IDao {

	@Override
	public final <T, K> List<T> queryList(String sql, K params, Class<T> clazz) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet rst = null;
		
		List<T> result = null;
				
		try {
//			System.out.println("AbstractQueryDao DB url:"+url);
//			System.out.println("AbstractQueryDao DB user name:"+userName);
			conn = DriverManager.getConnection(url, userName, password);
			prepStat = conn.prepareStatement(sql);
			
			//Type1: query
			prepStat = sqlParamsSetting(prepStat, params);
			
			rst = prepStat.executeQuery();
			
			//Rowmapping
			result = (List<T>) rowMapper(rst);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rst!=null) {
				try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(prepStat!=null) {
				try {
					prepStat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	@Override
	public final <K> int update(String sql, K params) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public <K> int[] updateBatch(String sql, K params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
