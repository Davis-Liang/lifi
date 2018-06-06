package com.nike.lifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nike.lifi.dao.BaseConfigDao;
import com.nike.lifi.entity.PPKBean;

@Repository("ppkDao")
public class PPKDaoImpl implements BaseConfigDao<PPKBean> {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public int insert(List<PPKBean> beanList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PPKBean> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormatFilePath() {
		// TODO Auto-generated method stub
		return null;
	}

}
