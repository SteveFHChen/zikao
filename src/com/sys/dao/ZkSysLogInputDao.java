package com.sys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.AbstractUpdateDao;
import com.sys.dto.SysLogDto;
import com.sys.dto.ZkSysLogDto;

public class ZkSysLogInputDao extends AbstractUpdateDao {

	private String sql = "INSERT INTO zk_sys_log(user_id, oper_code, detail) VALUES(?, ?, ?)";
	
	public int dao(ZkSysLogDto log) {
		return this.update(this.sql, log);
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		ZkSysLogDto p = (ZkSysLogDto) params;
		
		try {
			prepStat.setInt(1, p.getUserId());
			prepStat.setString(2, p.getOperCode());
			prepStat.setString(3, p.getDetail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prepStat;
	}

}
