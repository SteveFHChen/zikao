package com.sys.service;

import com.sys.dao.SysLogInputDao;
import com.sys.dto.SysLogDto;

public class SysLogService {

	private SysLogInputDao sysLogInputDao = new SysLogInputDao();
	
	public int sysLogInput(SysLogDto log) {
		return this.sysLogInputDao.dao(log);
	}
}
