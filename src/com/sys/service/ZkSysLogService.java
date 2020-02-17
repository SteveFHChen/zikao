package com.sys.service;

import com.sys.dao.ZkSysLogInputDao;
import com.sys.dto.ZkSysLogDto;

public class ZkSysLogService {

	private ZkSysLogInputDao zkSysLogInputDao = new ZkSysLogInputDao();
	
	public int sysLogInput(ZkSysLogDto log) {
		return this.zkSysLogInputDao.dao(log);
	}
}
