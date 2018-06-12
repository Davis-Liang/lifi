package com.nike.lifi.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nike.lifi.dao.UserBatchDao;

@Repository
public class UserBatchDaoImpl implements UserBatchDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public boolean insert(Integer userId, Integer batchId, Integer entityId) {
		String sql = "INSERT INTO stg_config_user_mapping (batch_id, user_id, entity_id, create_time, update_time) "
				+ "VALUES (:batchId, :userId, :entityId, now(), now())";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("batchId", batchId);
		namedParameters.put("userId", userId);
		namedParameters.put("entityId", entityId);
		return namedParameterJdbcTemplate.update(sql, namedParameters) > 0;
	}

	@Override
	public boolean update(Integer userId, Integer oriBatchId, Integer newBatchId, Integer entityId) {
		String sql = "UPDATE stg_config_user_mapping SET batch_id = :newBatchId, user_id = :userId, entity_id = :entityId, update_time = now()"
				+ "WHERE batch_id = :oriBatchId AND user_id = :userId AND entity_id = :entityId)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("oriBatchId", oriBatchId);
		namedParameters.put("newBatchId", newBatchId);
		namedParameters.put("userId", userId);
		namedParameters.put("entityId", entityId);
		return namedParameterJdbcTemplate.update(sql, namedParameters) > 0;
	}

	@Override
	public boolean delete(Integer userId, Integer entityId) {
		String sql = "DELETE FROM stg_config_user_mapping WHERE user_id = :userId AND entity_id = :entityId";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("userId", userId);
		namedParameters.put("entityId", entityId);
		return namedParameterJdbcTemplate.update(sql, namedParameters) > 0;
	}
	
	
}
