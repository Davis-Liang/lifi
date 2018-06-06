package com.nike.lifi.service;

import java.util.List;
import java.util.Map;

public interface BaseConfigService<T> {

	public List<T> list();
	
	public Map<String, Object> generateConfigMap();
}
