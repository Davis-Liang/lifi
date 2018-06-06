package com.nike.lifi.dao;

import java.util.List;

public interface BaseConfigDao<T> {

	public int insert(List<T> beanList);
	
	public List<T> list();
	
	public String getFormatFilePath();
}
