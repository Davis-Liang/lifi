package com.nike.lifi.processor;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nike.lifi.exception.ProcessorException;

public abstract class ConfigAbstractProcessor implements BaseProcessor {

	private InputStream fileInputStream;

	private Map<String, Object> sharedObject;

	private List<String> validationMessages = new LinkedList<String>();

	@Override
	public abstract boolean beforeProcess();

	@Override
	public abstract boolean process() throws ProcessorException;

	@Override
	public abstract boolean afterProcess();

	@Override
	public void init(InputStream fi, Map<String, Object> sharedObject) {
		this.sharedObject = sharedObject;
		this.fileInputStream = fi;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public Map<String, Object> getSharedObject() {
		return sharedObject;
	}

	public void setSharedObject(Map<String, Object> sharedObject) {
		this.sharedObject = sharedObject;
	}

	public List<String> getValidationMessages() {
		return validationMessages;
	}

	public void setValidationMessages(List<String> validationMessages) {
		this.validationMessages = validationMessages;
	}

}
