package org.refplus.dao.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.refplus.RefPlusException;

/**
 * First the system property LOCATION_NAME is checked. If defined, will be used. If not defined, the system environement
 * property LOCATION_NAME will be used.
 * 
 * @author Erik van Ingen
 *
 */

public class PropertyFileResolver {

	public static final String LOCATION_NAME = "LOCATION_NAME";
	public static final String FILE_NAME = "refplus-db.properties";

	private Map<String, String> properties = new HashMap<String, String>();

	public Map<String, String> getProperties() {
		return properties;
	}

	@PostConstruct
	private void init() throws IOException {
		File file = new File(getConfigurationFileName());

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			throw new RefPlusException("Unable to load properties file");
		}
		Map<String, String> hashMap = new HashMap(properties);

		this.properties.putAll(hashMap);
	}

	private String getConfigurationFileName() {
		// first check system property
		String propertyFile = System.getProperty(LOCATION_NAME);
		if (propertyFile == null) {
			// in case not system property is defined, the system environment property will be taken.
			propertyFile = System.getenv(LOCATION_NAME);
			if (propertyFile == null) {
				throw new RefPlusException(
						LOCATION_NAME + " not properly set (system property or system environment property)");
			}
		}
		propertyFile = propertyFile + File.separator + FILE_NAME;
		return propertyFile;
	}

}
