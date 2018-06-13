package com.nike.lifi.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nike.lifi.constants.LIFIConstants;

@SuppressWarnings("unchecked")
public abstract class ConfigAbstractController {

	public Map<String, Object> getSharedObject(HttpServletRequest request) {
		return (Map<String, Object>) request.getSession().getAttribute(LIFIConstants.SESSION_SHARED_OBJECT);
	};

	public Map<String, Object> getConfigMap(HttpServletRequest request, String configKey) {
		return (Map<String, Object>) this.getSharedObject(request).get(configKey);
	};
}
