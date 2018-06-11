package com.nike.lifi.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.nike.lifi.security.util.ControllerTools;

import net.sf.json.JSONObject;

public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		request.getSession().setAttribute(SPRING_SECURITY_CONTEXT, SecurityContextHolder.getContext());
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
