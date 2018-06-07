package com.nike.lifi.util;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SessionUserHelper {
	
	/**
	 * get current user name from spring security context
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
	 * TODO not implemented yet, hard code to 2 for test use
	 * @return
	 */
	public static Integer getCurrentUserId() {
		return 2;
	}
}
