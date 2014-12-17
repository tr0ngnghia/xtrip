/**
 * 
 */
package com.xtrip.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.xtrip.model.bean.BaseBean;
import com.xtrip.model.connectors.JavaJongo;
import com.xtrip.mongo.CommonConfigurationEntries;
import com.xtrip.mongo.CommonCoreController;

/**
 * @author longnh
 */
public abstract class BaseController {
	public static String TAG = "";
	public Date start;

	static {
		synchronized (JavaJongo.class) {
			Date start = Calendar.getInstance().getTime();
			Properties conf = CommonCoreController.getConfiguration();
			conf.setProperty(CommonConfigurationEntries.mongo_uri.getValue(),
					"mongodb://dbowner:forDevelopment!#@165.229.229.148/MetaphorChallenge");
			conf.setProperty(
					CommonConfigurationEntries.mongo_gridfs_enabled.getValue(),
					"false");
			JavaJongo.forceNewInstance();
			BaseBean.connector = JavaJongo.getInstance();
		}
	}

	public void ready() {
		start = Calendar.getInstance().getTime();
		// Log.info(TAG, "Start at: " + DateHelper.GMT.format(start));
	}

	public void stop() {
		Date stop = Calendar.getInstance().getTime();
		// Log.info(TAG, "Stop at: " + DateHelper.GMT.format(stop));
		Long interval = (stop.getTime() - start.getTime()) / 1000;
		Long hours = interval / 3600;
		Long minutes = interval / 60 - hours * 60;
		Long seconds = interval - hours * 3600 - minutes * 60;
		// Log.info(TAG,
		// String.format("Running time: %d hour, %d minute, %d seconds", hours,
		// minutes, seconds));
	}

	public abstract void run();
}
