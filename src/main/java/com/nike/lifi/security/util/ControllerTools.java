package com.nike.lifi.security.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ControllerTools {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(ajaxHeader);
	}

	public static void print(HttpServletResponse response, JSONObject returnObj) {
		try {
			response.getWriter().print(returnObj.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
