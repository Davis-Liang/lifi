package com.nike.lifi.processor;

import com.nike.lifi.exception.ProcessorException;

public interface BaseProcessor {
	
	public boolean beforeProcess();

	public boolean process() throws ProcessorException;
	
	public boolean afterProcess();
	
}
