package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.SystemParamDto;
import com.dto.SystemParamItemDto;

public class QuerySystemParamsDao extends AbstractQueryDao {

	private static String sql_main = "SELECT param_code, param_value, param_desc FROM sys_param ";
	private static String sql_order = " ORDER BY order_id";
	
	public List<SystemParamItemDto> dao(SystemParamDto params) {
		//Prepare dynamic SQL
		String sql = null;
		if(null==params.getSystemParams()) {
			sql = sql_main + sql_order;
		}else {
			StringBuffer sb = new StringBuffer();
			sb.append("?");
			for(int i=1; i<params.getSystemParams().size(); i++) {
				sb.append(",?");
			}
			sql = sql_main + " WHERE param_code in ("+sb.toString()+")" + sql_order;
		}
		
		List<SystemParamItemDto> result = this.queryList(sql, params, SystemParamItemDto.class);
		return result;
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		SystemParamDto p = (SystemParamDto) params;
		
		if(null!=p.getSystemParams()) {
			int i = 0;
			for(SystemParamItemDto item : p.getSystemParams()) {
				i++;
				try {
					prepStat.setString(i, item.getParamCode());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return prepStat;
	}

	@Override
	public List<SystemParamItemDto> rowMapper(ResultSet rst) throws SQLException {
		// TODO Auto-generated method stub
		List result = new ArrayList();
		SystemParamItemDto item = null;
		
		while(rst.next()) {
			item = new SystemParamItemDto(
					rst.getString("param_code"),
					rst.getString("param_value"),
					rst.getString("param_desc")
					);
			result.add(item);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		QuerySystemParamsDao querySystemParamsDao = new QuerySystemParamsDao();
		
		SystemParamDto params = new SystemParamDto();
		//Test 1: no specific item - query all
		List<SystemParamItemDto> result = querySystemParamsDao.dao(params);
		System.out.println(result);
		
		//Test 2: has specific items - query some
		List<SystemParamItemDto> items = new ArrayList<SystemParamItemDto>();
		items.add(new SystemParamItemDto("StartDateStr", null, null));
		items.add(new SystemParamItemDto("XXXX", null, null));
		
		params.setSystemParams(items);
		result = querySystemParamsDao.dao(params);
		System.out.println(result);
		
	}

}
