package com.xtrip.model.connectors;

import java.lang.reflect.Constructor;

import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.springframework.util.ClassUtils;

import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;

/**
 * @author longnh
 */
public class JavaJongo implements IMongoDBConnector {

	private static volatile JavaJongo INSTANCE = null;

	protected static String currentMongoDB = "";

	protected MongoClient mongo = null;
	protected Jongo jongo = null;
	protected GridFS gridfs = null;

	public JavaJongo(Properties config, ClassLoader classLoader,
			boolean isTestMode) throws Exception {

		String clientFactoryName = config
				.getProperty("mongo.mongoClientFactory");
		MongoClientFactory factory = getMongoClientFactory(clientFactoryName,
				config, isTestMode);
		mongo = factory.createClient();

		if (mongo == null) {
			throw new IllegalStateException(
					"No MongoClient was created by instance of "
							+ factory.getClass().getName());
		}

		DB db = mongo.getDB("".equals(currentMongoDB) ? factory.getDBName()
				: currentMongoDB);

		jongo = new Jongo(db, createMapper(config, classLoader));

		if (Boolean.parseBoolean(config.getProperty("mongo.gridfs.enabled")) == false) {
			gridfs = new GridFS(jongo.getDatabase());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected MongoClientFactory getMongoClientFactory(String className,
			Properties config, boolean isTestMode) throws Exception {

		if (className != null) {
			try {
				Class factoryClass = Class.forName(className, true, Thread
						.currentThread().getContextClassLoader());
				if (!MongoClientFactory.class.isAssignableFrom(factoryClass)) {
					throw new IllegalStateException("mongoClientFactory '"
							+ className + "' is not of type "
							+ MongoClientFactory.class.getName());
				}

				Constructor constructor = null;
				try {
					constructor = factoryClass.getConstructor(Properties.class);
				} catch (Exception e) {
					// can't use that one
				}
				if (constructor == null) {
					return (MongoClientFactory) factoryClass.newInstance();
				}
				return (MongoClientFactory) constructor.newInstance(config);
			} catch (ClassNotFoundException e) {
				throw e;
			}
		}
		return new MongoClientFactory(config, isTestMode);
	}

	private Mapper createMapper(Properties config, ClassLoader classLoader)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		final String factoryClassName = config.getProperty(
				"mongo.mapperfactory",
				JongoMapperFactory.DefaultFactory.class.getName());
		JongoMapperFactory factory = (JongoMapperFactory) Class.forName(
				factoryClassName, true, classLoader).newInstance();
		return factory.create();
	}

	public static JavaJongo getInstance() {
		if (INSTANCE == null) {
			synchronized (JavaJongo.class) {
				if (INSTANCE == null) {
					try {
						INSTANCE = new JavaJongo(
								com.xtrip.mongo.CommonCoreController
										.getConfiguration(),
								//ke.yu.ac.kr.controller.mongo.CommonCoreController.classLoader(),
										ClassUtils.getDefaultClassLoader(),
								com.xtrip.mongo.CommonCoreController
										.isTest());
					} catch (Exception e) {
						// Logger.error(e.getClass().getSimpleName(), e);
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		return INSTANCE;
	}

	public static void switchDatabase(String db) throws Exception {
		if (db == null || "".equals(db)) {
			String errMessage = "The mongo database name is invalid";
			// Logger.error(mongo.class.getSimpleName(), errMessage);
			throw new Exception(errMessage);
		}

		if (!JavaJongo.currentMongoDB.equals(db)) {
			JavaJongo.currentMongoDB = db;
			INSTANCE.mongo.close();
			INSTANCE = null;
			INSTANCE = getInstance();
		}

	}

	public static void forceNewInstance() {
		INSTANCE = null;
		getInstance();
	}

	public static Mongo mongo() {
		return getInstance().mongo;
	}

	public static Jongo jongo() {
		return getInstance().jongo;
	}

	public static GridFS gridfs() {
		return getInstance().gridfs;
	}

	public MongoCollection getCollection(String name) {
		return getInstance().jongo.getCollection(name);
	}

	public static DB getDatabase() {
		return getInstance().jongo.getDatabase();
	}
}