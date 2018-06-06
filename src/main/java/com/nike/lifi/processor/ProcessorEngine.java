package com.nike.lifi.processor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.nike.lifi.exception.ProcessorException;

public final class ProcessorEngine {

	public static void process(BaseProcessor baseProcessor) {
		if (baseProcessor.beforeProcess()) {
			try {
				baseProcessor.process();
			} catch (ProcessorException e) {
				// TODO Error handle
			} finally {
				baseProcessor.afterProcess();
			}
		}
	}

	public static void process(List<BaseProcessor> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			list.stream().forEach(p -> process(p));
		}
	}
}
