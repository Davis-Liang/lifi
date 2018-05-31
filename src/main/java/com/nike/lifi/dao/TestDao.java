package com.nike.lifi.dao;

import java.util.List;

import com.nike.lifi.entity.TestBean;

public interface TestDao {

	public int insert();

	public List<TestBean> list();
	
	public TestBean getById(Integer id);
}
