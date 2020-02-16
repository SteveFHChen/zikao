package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDao {

	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params);
	
	public <T,K> List<T> queryList(String sql, K params, Class<T> clazz);
	
	public <T> List<T> rowMapper(ResultSet rst) throws SQLException;
	
	public <K> int update(String sql, K params);
	
	public <K> int[] updateBatch(String sql, K params);
}
