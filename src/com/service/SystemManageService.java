package com.service;

import java.util.List;

import com.dao.QuerySystemParamsDao;
import com.dao.UpdateSystemParamsDao;
import com.dto.SystemParamDto;
import com.dto.SystemParamItemDto;

public class SystemManageService {

	private QuerySystemParamsDao querySystemParamsDao = new QuerySystemParamsDao();
	private UpdateSystemParamsDao updateSystemParamsDao = new UpdateSystemParamsDao();
	
	public List<SystemParamItemDto> querySystemParmas(SystemParamDto params) {
		return querySystemParamsDao.dao(params);
	}
	
	public List<SystemParamItemDto> updateSystemParams(SystemParamDto params){
		int[] updateCount = updateSystemParamsDao.dao(params);
		return querySystemParmas(params);
	}
}
