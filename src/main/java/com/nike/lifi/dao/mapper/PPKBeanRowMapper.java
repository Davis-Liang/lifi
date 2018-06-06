package com.nike.lifi.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nike.lifi.entity.PPKBean;

public class PPKBeanRowMapper implements RowMapper<PPKBean> {

	@Override
	public PPKBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PPKBean ppkBean = new PPKBean();
		ppkBean.setProdCd(rs.getString("prod_cd"));
		ppkBean.setPpk(rs.getString("ppk"));
		return ppkBean;
	}

}
