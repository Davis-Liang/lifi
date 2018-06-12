package com.nike.lifi.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.nike.lifi.constants.LIFIConstants;
import com.nike.lifi.security.util.ControllerTools;
import com.nike.lifi.util.SessionUserHelper;

import net.sf.json.JSONObject;

public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Map<String, Object> sharedObject = new HashMap<String, Object>();
		request.getSession(true).setAttribute(LIFIConstants.SESSION_SHARED_OBJECT, sharedObject);
		sharedObject.put(LIFIConstants.SESSION_USER_ID, SessionUserHelper.getCurrentUserId());
		boolean isAjax = ControllerTools.isAjaxRequest(request);
		if (isAjax) {
			String principal = authentication.getPrincipal().toString();
			JSONObject returnObj = new JSONObject();
			returnObj.put("status", HttpServletResponse.SC_OK);
			returnObj.put("data", principal);
			ControllerTools.print(response, returnObj);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
