/**
 * 
 */
package com.xtrip.model.connectors;

import org.jongo.MongoCollection;

/**
 * @author longnh
 */
public interface IMongoDBConnector {
	MongoCollection getCollection(String string);
}
