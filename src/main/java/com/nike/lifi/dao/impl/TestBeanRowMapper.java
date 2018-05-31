package com.nike.lifi.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nike.lifi.entity.TestBean;

public class TestBeanRowMapper implements RowMapper<TestBean> {

	@Override
	public TestBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		TestBean testBean = new TestBean();
		testBean.setName(rs.getString("name"));
		return testBean;
	}

}
