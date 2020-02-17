package com.sys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.AbstractUpdateDao;
import com.sys.dto.SysLogDto;

public class SysLogInputDao extends AbstractUpdateDao {

	private String sql = "INSERT INTO sys_log(remote_ip, login_user_id, url) VALUES(?, ?, ?);";
	
	public int dao(SysLogDto log) {
		return this.update(this.sql, log);
	}
	
	@Override
	public PreparedStatement sqlParamsSetting(PreparedStatement prepStat, Object params) {
		// TODO Auto-generated method stub
		SysLogDto p = (SysLogDto) params;
		
		try {
			prepStat.setString(1, p.getRemoteIp());
			prepStat.setInt(2, p.getLoginUserId());
			prepStat.setString(3, p.getUrl());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prepStat;
	}

}
