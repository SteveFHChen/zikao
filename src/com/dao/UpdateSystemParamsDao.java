package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.dto.SystemParamDto;
import com.dto.SystemParamItemDto;

public class UpdateSystemParamsDao extends AbstractUpdateDao {
	
	private static String sql = "UPDATE sys_param SET param_value=?, param_desc=? WHERE param_code=?";

	public int[] dao(SystemParamDto params){
		int[] n = updateBatch(sql, params);
		return n;
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		SystemParamDto p = (SystemParamDto) params;
		List<SystemParamItemDto> items = p.getSystemParams();
		try {
			for(SystemParamItemDto item : items) {
				prepStat.setString(1, item.getParamValue());
				prepStat.setString(2, item.getParamDesc());
				prepStat.setString(3, item.getParamCode());
				prepStat.addBatch();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepStat;
	}

}
