package com.xtrip.mongo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.xtrip.model.BaseModel;
import com.xtrip.model.connectors.JavaJongo;

/**
 * @author longnh
 */
public class CommonCoreController {
	public static Properties configuration = new Properties();

	/**
	 * Load Configuration
	 * @param conf
	 */
	public static void fore2LoadConfiguration(Properties conf) {
		if (conf == null || conf.size() == 0) {
			synchronized (CommonCoreController.class) {
				Properties result = new Properties();
				InputStream in = CommonCoreController.class
						.getResourceAsStream("config.properties");
				try {
					result.load(in);
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				configuration = result;
			}
		}else
		{
			configuration = conf;
		}
		
		BaseModel.connector = JavaJongo.getInstance();
	}

	public static void setConfiguration(Properties conf) {
		configuration = conf;
	}

	public static ClassLoader classLoader() {
		return ClassLoader.getSystemClassLoader();
	}

	public static Boolean isTest() {
		if (!configuration
				.containsKey(CommonConfigurationEntries.application_is_test
						.getValue()))
			return false;
		return Boolean.parseBoolean(configuration
				.getProperty(CommonConfigurationEntries.application_is_test
						.getValue()));
	}

	public static Properties getConfiguration() {
		return configuration;
	}
	
	public static String getValue(IConfigurationEntries val){
		if (configuration != null && configuration.containsKey(val.getValue())){
			return configuration.getProperty(val.getValue());
		}
		return "";
	}
}
