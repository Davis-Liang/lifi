package com.nike.lifi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nike.lifi.constants.LIFIConstants;
import com.nike.lifi.entity.PPKBean;
import com.nike.lifi.processor.PPKProcessor;
import com.nike.lifi.processor.ProcessorEngine;
import com.nike.lifi.service.BaseConfigService;

@Controller
@RequestMapping("/config/ppk")
@SuppressWarnings("unchecked")
public class PPKController {
	
	@Resource(name="ppkService")
	private BaseConfigService<PPKBean> ppkService;
	
	@Resource
	private PPKProcessor processor;

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {
		Map<String, Object> sharedObject = (Map<String, Object>) request.getSession().getAttribute(LIFIConstants.SESSION_SHARED_OBJECT);
		
		////////////////TEST USE/////////////////////
		if (null == sharedObject) {
			sharedObject = new HashMap<String, Object>();
			request.getSession().setAttribute(LIFIConstants.SESSION_SHARED_OBJECT, sharedObject);
		}
		/////////////////////////////////////////////
		
		if (sharedObject.containsKey(LIFIConstants.CONFIG_SHEET_PPK)) {
			Map<String, Object> ppkMap = (Map<String, Object>) sharedObject.get(LIFIConstants.CONFIG_SHEET_PPK);
			if (ppkMap.containsKey(LIFIConstants.CONFIG_SHEET_DATA)) {
				// directly show the data from session
			} else {
				List<PPKBean> list = ppkService.list();
				ppkMap.put(LIFIConstants.CONFIG_SHEET_DATA, list);
			}
		} else {
			sharedObject.put(LIFIConstants.CONFIG_SHEET_PPK, ppkService.generateConfigMap());
			// show the data from the result above
		}
		return "index";
	}
	
	@RequestMapping("/submit")
	public String submit(HttpServletRequest request) {
		ProcessorEngine.process(processor);
		return "index";
	}
	
	@RequestMapping("/confirm")
	public String confirm(HttpServletRequest request) {
		Map<String, Object> sharedObject = (Map<String, Object>) request.getSession().getAttribute(LIFIConstants.SESSION_SHARED_OBJECT);
		if (sharedObject.containsKey(LIFIConstants.CONFIG_SHEET_PPK)) {
			Map<String, Object> ppkMap = (Map<String, Object>) sharedObject.get(LIFIConstants.CONFIG_SHEET_PPK);
			ppkMap.put(LIFIConstants.CONFIG_SHEET_BYPASS, new Boolean(true));
		}
		return "index";
	}
}
