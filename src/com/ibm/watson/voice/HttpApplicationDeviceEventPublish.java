package com.ibm.watson.voice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.ibm.iotf.client.app.ApplicationClient;

/**
 * 
 * This sample shows how an application publish a device event using HTTP(s) to
 * IBM Watson IoT Platform on behalf of the device.
 *
 */
public class HttpApplicationDeviceEventPublish {

	private final static String PROPERTIES_FILE_NAME = "/application.properties";
	private final static Properties PROPERTIES_ENTRY = getProperties();

	public static Properties getProperties() {
		/**
		 * Load device properties
		 */
		Properties props = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}

	public void publishEvent(JsonObject data) {
		/**
		 * Get the Device Type and Device Id on behalf the application will
		 * publish the event
		 */
		String deviceType = trimedValue(PROPERTIES_ENTRY.getProperty("Device-Type"));
		String deviceId = trimedValue(PROPERTIES_ENTRY.getProperty("Device-ID"));

		ApplicationClient myClient = null;
		try {
			// Instantiate the class by passing the properties file
			myClient = new ApplicationClient(PROPERTIES_ENTRY);
			myClient.connect(5);

			boolean code = myClient.publishEvent(deviceType, deviceId, "data_car", data, "json", 1);
			if (code == true) {
				System.out.println("Published the event successfully !");
			} else {
				System.out.println("Failed to publish the event......");
			}
			myClient.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String trimedValue(String value) {
		if (value != null) {
			return value.trim();
		}
		return value;
	}

}