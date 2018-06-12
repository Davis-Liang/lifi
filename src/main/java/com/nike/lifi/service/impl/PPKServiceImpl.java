package com.nike.lifi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nike.lifi.constants.LIFIConstants;
import com.nike.lifi.dao.BaseConfigDao;
import com.nike.lifi.entity.PPKBean;
import com.nike.lifi.service.BaseConfigService;

@Service("ppkService")
public class PPKServiceImpl implements BaseConfigService<PPKBean> {

	@Resource(name = "ppkDao")
	private BaseConfigDao<PPKBean> ppkDao;

	@Override
	public List<PPKBean> list(Integer userId) {
		return ppkDao.list(userId);
	}

	@Override
	public Map<String, Object> generateConfigMap(Integer userId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(LIFIConstants.CONFIG_SHEET_DATA, ppkDao.list(userId));
		returnMap.put(LIFIConstants.CONFIG_SHEET_BYPASS, new Boolean(false));
		returnMap.put(LIFIConstants.CONFIG_SHEET_FORMAT_PATH, ppkDao.getFormatFilePath());
		return returnMap;
	}

	@Override
	public String getFormatFilePath() {
		return ppkDao.getFormatFilePath();
	}

}
