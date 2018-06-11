package com.nike.lifi.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.nike.lifi.dao.SysUsersDao;
import com.nike.lifi.security.SysUser;

@Repository
public class SysUsersDaoImpl implements SysUsersDao {

	@Resource
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SysUser getByUsername(String username) {
		String sql = "SELECT id, username, password, status FROM ddr.dim_user WHERE username = :username AND status = 1";

		SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);

		SysUser user = (SysUser) namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper(SysUser.class));

		return user;
	}

	@Override
	public Collection<GrantedAuthority> loadUserAuthorities(String username) {
		String sql = "SELECT u.username, r.role FROM ddr.dim_user u, ddr.stg_user_role ur, ddr.dim_role r "
				+ "WHERE u.id = ur.user_Id AND r.id = ur.role_Id AND u.username = :username ";

		SqlParameterSource namedParameters = new MapSqlParameterSource("username", username);

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		namedParameterJdbcTemplate.query(sql, namedParameters, rs -> {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rs.getString(2));
			grantedAuthorities.add(grantedAuthority);
		});

		return grantedAuthorities;
	}
	
	@Override
	public List<Map<String, String>> getURLResourceMapping() {
		String sql = "SELECT re.resource_value, r.role FROM ddr.dim_role r JOIN ddr.stg_resource_role rr ON r.id = rr.role_id "
				+ "JOIN ddr.dim_resource re ON re.id = rr.resource_id ORDER BY re.priority";

		List<Map<String, String>> resourceMapping = new ArrayList<Map<String, String>>();

		namedParameterJdbcTemplate.query(sql, rs -> {
			Map<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("resource_value", rs.getString(1));
			tmpMap.put("role", rs.getString(2));
			resourceMapping.add(tmpMap);
		});

		return resourceMapping;
	}

}
