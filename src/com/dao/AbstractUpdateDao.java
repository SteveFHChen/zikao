package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractUpdateDao extends CommonDao implements IDao {
	
	@Override
	public final <T, K> List<T> queryList(String sql, K params, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final <K> int update(String sql, K params) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet rst = null;
		
		int result = -1;
				
		try {
//			System.out.println("AbstractUpdateDao DB url:"+url);
//			System.out.println("AbstractUpdateDao DB user name:"+userName);
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(true);
			prepStat = conn.prepareStatement(sql);
			
			//Type2: update
			prepStat = sqlParamsSetting(prepStat, params);
			int n = prepStat.executeUpdate();
//			int[] n = prepStat.executeBatch();
			
			result = n;
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
	public final <K> int[] updateBatch(String sql, K params) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement prepStat = null;
		ResultSet rst = null;
		
		int[] result = null;
				
		try {
			conn = DriverManager.getConnection(url, userName, password);
			prepStat = conn.prepareStatement(sql);
			
			//Type2: update
			prepStat = sqlParamsSetting(prepStat, params);
//			int n = prepStat.executeUpdate();
			int[] n = prepStat.executeBatch();
			
			result = n;
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
	public final <T> List<T> rowMapper(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
