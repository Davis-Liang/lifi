package com.nike.lifi.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.nike.lifi.security.util.ControllerTools;

import net.sf.json.JSONObject;

public class AjaxAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		boolean isAjax = ControllerTools.isAjaxRequest(request);
		if (isAjax) {
			JSONObject returnObj = new JSONObject();
			returnObj.put("status", HttpServletResponse.SC_UNAUTHORIZED);
			ControllerTools.print(response, returnObj);
		} else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}
}
