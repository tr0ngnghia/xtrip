/**
 * 
 */
package com.xtrip.script;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtrip.model.BaseModel;
import com.xtrip.model.connectors.JavaJongo;
import com.xtrip.mongo.CommonConfigurationEntries;
import com.xtrip.mongo.CommonCoreController;
import com.xtrip.utils.DateHelper;

/**
 * @author longnh
 */
public abstract class BasicTask {
	private static final Logger logger = LoggerFactory
			.getLogger(BasicTask.class);

	public Date start;

	static {
		synchronized (JavaJongo.class) {
			Properties conf = CommonCoreController.getConfiguration();
			conf.setProperty(CommonConfigurationEntries.mongo_uri.getValue(),
//					"mongodb://admin:HTik0dL2@SG-xtrip-4111.servers.mongodirector.com:27017/admin");
					"mongodb://localhost:27017/xtrip");
			conf.setProperty(
					CommonConfigurationEntries.mongo_gridfs_enabled.getValue(),
					"false");
			JavaJongo.forceNewInstance();
			BaseModel.connector = JavaJongo.getInstance();
		}
	}

	public void ready() {
		start = Calendar.getInstance().getTime();
		logger.info("Start at: " + DateHelper.GMT.format(start));
	}

	public void stop() {
		Date stop = Calendar.getInstance().getTime();
		logger.info("Stop at: " + DateHelper.GMT.format(stop));
		Long interval = (stop.getTime() - start.getTime()) / 1000;
		Long hours = interval / 3600;
		Long minutes = interval / 60 - hours * 60;
		Long seconds = interval - hours * 3600 - minutes * 60;
		logger.info(String.format(
				"Running time: %d hour, %d minute, %d seconds", hours, minutes,
				seconds));
	}

	public abstract void run();
}
