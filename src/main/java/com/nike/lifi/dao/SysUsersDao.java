package com.nike.lifi.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.nike.lifi.security.entity.SysUser;

public interface SysUsersDao {
	public SysUser getByUsername(String username);

	public Collection<GrantedAuthority> loadUserAuthorities(String username);
	
	public List<Map<String,String>> getURLResourceMapping();
}
