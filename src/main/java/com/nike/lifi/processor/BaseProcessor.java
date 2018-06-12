package com.nike.lifi.processor;

import java.io.InputStream;
import java.util.Map;

import com.nike.lifi.exception.ProcessorException;

public interface BaseProcessor {
	
	public boolean beforeProcess();

	public boolean process() throws ProcessorException;
	
	public boolean afterProcess();
	
	public void init(InputStream fi, Map<String, Object> sharedObject);
	
}
