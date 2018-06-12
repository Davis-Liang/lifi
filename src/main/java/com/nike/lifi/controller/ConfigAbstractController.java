package com.nike.lifi.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nike.lifi.constants.LIFIConstants;

public abstract class ConfigAbstractController {
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSharedObject(HttpServletRequest request) {
		return (Map<String, Object>) request.getSession().getAttribute(LIFIConstants.SESSION_SHARED_OBJECT);
	};
	
	
}
