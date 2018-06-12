package com.nike.lifi.util;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.nike.lifi.security.entity.SysUser;

public class SessionUserHelper {

	/**
	 * get current user name from spring security context
	 * 
	 * @return
	 */
	public static String getCurrentUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		if (principal instanceof Principal) {
			return ((Principal) principal).getName();
		}
		return String.valueOf(principal);

		// another way
		// return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	/**
	 * get current user id from spring security context 
	 * 
	 * @return
	 */
	public static Integer getCurrentUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			if (principal instanceof UserDetails) {
				String userId = ((SysUser) principal).getId();
				return Integer.parseInt(userId);
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
