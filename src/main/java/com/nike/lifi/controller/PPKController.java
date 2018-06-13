package com.nike.lifi.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.nike.lifi.constants.LIFIConstants;
import com.nike.lifi.entity.PPKBean;
import com.nike.lifi.processor.PPKProcessor;
import com.nike.lifi.processor.ProcessorEngine;
import com.nike.lifi.service.BaseConfigService;
import com.nike.lifi.util.SessionUserHelper;

@Controller
@RequestMapping("/config/ppk")
@SuppressWarnings("unchecked")
public class PPKController extends ConfigAbstractController {

	@Resource(name = "ppkService")
	private BaseConfigService<PPKBean> ppkService;

	@RequestMapping("/show")
	public String show(HttpServletRequest request) {

		Map<String, Object> sharedObject = getSharedObject(request);

		Integer userId = SessionUserHelper.getCurrentUserId();

		if (sharedObject.containsKey(LIFIConstants.CONFIG_SHEET_PPK)) {
			Map<String, Object> ppkMap = getConfigMap(request, LIFIConstants.CONFIG_SHEET_PPK);
			if (!ppkMap.containsKey(LIFIConstants.CONFIG_SHEET_DATA)) {
				List<PPKBean> list = ppkService.list(userId);
				ppkMap.put(LIFIConstants.CONFIG_SHEET_DATA, list);
			}
		} else {
			sharedObject.put(LIFIConstants.CONFIG_SHEET_PPK, ppkService.generateConfigMap(userId));
		}
		request.setAttribute("data", getConfigMap(request, LIFIConstants.CONFIG_SHEET_PPK).get(LIFIConstants.CONFIG_SHEET_DATA));
		return "ppk";
	}

	@RequestMapping("/submit")
	public String submit(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
		WebApplicationContext appContext = ContextLoader.getCurrentWebApplicationContext();
		PPKProcessor processor = appContext.getBean(PPKProcessor.class);
		processor.init(file.getInputStream(), getSharedObject(request));
		ProcessorEngine.process(processor);
		return "forward:/config/ppk/show.do";
	}

	@RequestMapping("/confirm")
	public String confirm(HttpServletRequest request) {
		Map<String, Object> sharedObject = getSharedObject(request);
		if (sharedObject.containsKey(LIFIConstants.CONFIG_SHEET_PPK)) {
			getConfigMap(request, LIFIConstants.CONFIG_SHEET_PPK).put(LIFIConstants.CONFIG_SHEET_BYPASS, new Boolean(true));
		}
		return "forward:/config/ppk/show.do";
	}

	@RequestMapping(value = "/template")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String utf = "UTF-8";

		String fileName = ppkService.getFormatFilePath();
		String path = request.getServletContext().getRealPath("/");
		File file = new File(path + File.separator + fileName);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setCharacterEncoding(utf);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30");
		response.setHeader("Content-Disposition", "attachment; filename=PPK_Template.xlsx");

		byte[] buffer = new byte[4096];
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			while ((n = input.read(buffer, 0, 4096)) != -1) {
				output.write(buffer, 0, n);
			}
			output.flush();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO
		} finally {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
		}
	}

	@RequestMapping(value = "/history")
	public void downloadHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO write Sheet -- pending on Excel Util
		Map<String, Object> sharedObject = getSharedObject(request);
		List<PPKBean> list = (List<PPKBean>) getConfigMap(request, LIFIConstants.CONFIG_SHEET_PPK).get(LIFIConstants.CONFIG_SHEET_DATA);

	}
}
