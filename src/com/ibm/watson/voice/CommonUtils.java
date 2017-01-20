package com.ibm.watson.voice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtils {

	public static final String getPropertyValue (String key) {
		Properties properties = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("/config.properties");
		try {
			properties.load(input);
			return properties.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final String getPropertyValue (String... keys) {
		StringBuffer buffer = new StringBuffer();
		for (String key : keys) {
			buffer.append(getPropertyValue(key));
		}
		return buffer.toString();
	}
}
