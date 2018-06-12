package com.nike.lifi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.nike.lifi.constants.ConfigEntityConstants;
import com.nike.lifi.dao.BaseConfigDao;
import com.nike.lifi.entity.PPKBean;

@Repository("ppkDao")
public class PPKDaoImpl implements BaseConfigDao<PPKBean> {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Value("${lifi.template.ppk}")
	private String ppkTemplateFilePath;

	@Override
	public int getNextVal(String seqName) {
		String sql = "SELECT nextval(:seqName)";
		SqlParameterSource namedParameters = new MapSqlParameterSource("seqName", seqName);
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insert(List<PPKBean> beanList) {
		int batchId = this.getNextVal("batch_id");
		String sql="insert into lifi.dim_config_sheet_ppk(batch_id, prod_code, ppk, create_time) " +   
	            " values(:batchId, :prodCode, :ppk, now())";
		Map<String, Object>[] namedParameters = new HashMap[beanList.size()];
		for (int i = 0; i < beanList.size(); i++) {
			PPKBean curBean = beanList.get(i);
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("batchId", batchId);
			tmpMap.put("prodCode", curBean.getProdCd());
			tmpMap.put("ppk", curBean.getPpk());
			namedParameters[i] = tmpMap;
		}
		namedParameterJdbcTemplate.batchUpdate(sql, namedParameters);
		return batchId;
	}

	@Override
	public List<PPKBean> list(Integer userId) {
		String sql = "SELECT ppk.prod_code, ppk.ppk FROM ddr.dim_user u, lifi.dim_config_sheet_ppk ppk, lifi.stg_config_user_mapping m "
				+ "WHERE u.id = :uid AND u.id = m.user_id AND m.batch_id = ppk.batch_id and m.entity_id = :entityId";

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("uid", userId).addValue("entityId",
				ConfigEntityConstants.ENTITY_SHEET_PPK);

		List<PPKBean> retList = new ArrayList<PPKBean>();
		namedParameterJdbcTemplate.query(sql, namedParameters, rs -> {
			PPKBean ppkBean = new PPKBean();
			ppkBean.setProdCd(rs.getString(1));
			ppkBean.setPpk(rs.getString(2));
			retList.add(ppkBean);
		});
		return retList;
	}

	@Override
	public String getFormatFilePath() {
		return this.ppkTemplateFilePath;
	}

	@Override
	public int deleteByUserIdAndEntityId(Integer userId, Integer entityId) {
		String sql = "DELETE FROM lifi.dim_config_sheet_ppk WHERE batch_id = ("
				+ "SELECT batch_id FROM lifi.stg_config_user_mapping WHERE user_id = :userId AND entity_id = :entityId limit 1)";
		Map<String, Integer> namedParameters = new HashMap<String, Integer>();
		namedParameters.put("userId", userId);
		namedParameters.put("entityId", entityId);
		return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

}
