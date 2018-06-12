package com.nike.lifi.dao;

public interface UserBatchDao {

	public boolean insert(Integer userId, Integer batchId, Integer entityId);
	
	public boolean update(Integer userId, Integer oriBatchId, Integer newBatchId, Integer entityId);
	
	public boolean delete(Integer userId, Integer entityId);
}
