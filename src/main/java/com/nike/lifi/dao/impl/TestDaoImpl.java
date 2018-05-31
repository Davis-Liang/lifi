package com.nike.lifi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.nike.lifi.dao.TestDao;
import com.nike.lifi.entity.TestBean;

@Repository
public class TestDaoImpl implements TestDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int insert() {
		String SQL = "INSERT INTO TEST (name) VALUES (:name)";
		Map<String, String> namedParameters = new HashMap<String, String>();
		namedParameters.put("name", "ClayTest");
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}

	@Override
	public List<TestBean> list() {
		String SQL = "SELECT * FROM TEST";
		List<TestBean> testBeans = (List<TestBean>) namedParameterJdbcTemplate.query(SQL, new TestBeanRowMapper());
		return testBeans;
	}

	@Override
	public TestBean getById(Integer id) {
		String SQL = "SELECT * FROM TEST WHERE id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", 1);
		TestBean testBean = (TestBean) namedParameterJdbcTemplate.queryForObject(SQL, namedParameters,
				new TestBeanRowMapper());
		return testBean;
	}

}
