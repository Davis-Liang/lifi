package com.nike.lifi.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;

public class MessageSourceHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static String getMessage(String code) {
		return getMessage(code, (Object[]) null, (Locale) null);
	}

	public static String getMessage(String code, String defaultMessage) {
		return getMessage(code, (Object[]) null, defaultMessage, (Locale) null);
	}

	public static String getMessage(String code, Object[] args) {
		return getMessage(code, args, (Locale) null);
	}

	public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return applicationContext.getMessage(code, args, defaultMessage, locale);
	}

	public static String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		return applicationContext.getMessage(code, args, locale);
	}

}
