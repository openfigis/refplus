package org.refplus.dao.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.refplus.RefPlusException;

@Singleton
public class PropertyFileResolver {

	public static final String LOCATION_NAME = "LOCATION_NAME";
	public static final String FILE_NAME = "refplus-db.properties";

	private Map<String, String> properties = new HashMap<String, String>();

	@PostConstruct
	private void init() throws IOException {
		String propertyFile = System.getProperty(LOCATION_NAME);
		if (propertyFile == null) {
			throw new RefPlusException(LOCATION_NAME + " not properly set (system property)");
		}
		File file = new File(propertyFile + File.separator + FILE_NAME);

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
