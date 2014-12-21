/**
 * 
 */
package com.xtrip.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtrip.model.bean.BLocation;
import com.xtrip.model.bean.BaseBean;
import com.xtrip.model.connectors.JavaJongo;
import com.xtrip.mongo.CommonConfigurationEntries;
import com.xtrip.mongo.CommonCoreController;

/**
 * @author longnh
 */
public class TestConnection {
	private static String TAG = "TestConnection";
	private static final Logger logger = LoggerFactory
			.getLogger(TestConnection.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		synchronized (JavaJongo.class) {
			Date start = Calendar.getInstance().getTime();
			logger.info("START++++++++++++++++++++++++++");
			Properties conf = CommonCoreController.getConfiguration();
			conf.setProperty(
					CommonConfigurationEntries.mongo_uri.getValue(),
					"mongodb://admin:Zrx5Lnun@SG-xtrip-4108.servers.mongodirector.com:27017/admin");
			conf.setProperty(
					CommonConfigurationEntries.mongo_gridfs_enabled.getValue(),
					"false");
			JavaJongo.forceNewInstance();
			BaseBean.connector = JavaJongo.getInstance();
			
			BLocation blocation = new BLocation();
			blocation.save();

			logger.info("END++++++++++++++++++++++++++");
			Long interval = (Calendar.getInstance().getTimeInMillis() - start
					.getTime()) / 1000;
			Long hours = interval / 3600;
			Long minutes = interval / 60 - hours * 60;
			Long seconds = interval - hours * 3600;
			logger.info(String.format(
					"Running time: %d hour, %d minute, %d seconds", hours,
					minutes, seconds));
		}
	}
}
