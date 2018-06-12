package com.nike.lifi.dao;

import java.util.List;

public interface BaseConfigDao<T> {
	
	public int getNextVal(String seqName);

	public int insert(List<T> beanList);
	
	public List<T> list(Integer userId);
	
	public String getFormatFilePath();
	
	public int deleteByUserIdAndEntityId(Integer userId, Integer entityId);
}
