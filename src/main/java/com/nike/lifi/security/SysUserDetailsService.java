package com.nike.lifi.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nike.lifi.constants.LdapConfig;
import com.nike.lifi.dao.SysUsersDao;
import com.nike.lifi.security.entity.SysUser;

public class SysUserDetailsService implements UserDetailsService {

	@Resource
	private LdapConfig ldapConfig;

	@Resource
	private SysUsersDao sysUsersDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUsersDao.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserDetailsService.userNotFount");
		}
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths = sysUsersDao.loadUserAuthorities(username);
		user.setAuthorities(auths);
		return user;
	}

}
