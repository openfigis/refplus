package org.refplus.dao.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class PropertyFileResolver {

	private Map<String, String> properties = new HashMap<>();

	@PostConstruct
	private void init() throws IOException {
		String propertyFile = System.getProperty("refplus.properties");
		File file = new File(propertyFile);
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			System.out.println("Unable to load properties file" + e);
		}

		Map<String, String> hashMap = new HashMap(properties);
		this.properties.putAll(hashMap);
	}

	public String getProperty(String key) {
		return properties.get(key);
	}
}
