package com.xtrip.model.connectors;


import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;

/**
 * @author longnh
 */
public class MongoClientFactory {

    protected Properties config;
    protected boolean isTest;

    public MongoClientFactory(Properties config) {
        this.config = config;
    }

    protected MongoClientFactory(Properties config, boolean isTest) {
        this.config = config;
        this.isTest = isTest;
    }

    /**
     * Creates and returns a new instance of a MongoClient.
     *
     * @return a new MongoClient
     * @throws Exception
     */
    public MongoClient createClient() throws Exception {
        MongoClientURI uri = getClientURI();

        MongoClient mongo = new MongoClient(uri);
        DB db = mongo.getDB(uri.getDatabase());

        // Set write concern if configured
        String defaultWriteConcern = config.getProperty("mongo.defaultWriteConcern");                
        if(defaultWriteConcern != null) {
            db.setWriteConcern(WriteConcern.valueOf(defaultWriteConcern));
        }

        // Authenticate the user if necessary
        if (uri.getUsername() != null) {
            db.authenticate(uri.getUsername(), uri.getPassword());
        }
        
        return mongo;
    }

    /**
     * Returns the database name associated with the current Properties.
     *
     * @return The database name
     */
    public String getDBName() {
        return getClientURI().getDatabase();
    }

    protected MongoClientURI getClientURI() {
        MongoClientURI uri = new MongoClientURI(
                isTest
                    ? config.getProperty("mongo.test-uri", "mongodb://admin:HTik0dL2@SG-xtrip-4111.servers.mongodirector.com:27017/admin")
                    : config.getProperty("mongo.uri", "mongodb://admin:HTik0dL2@SG-xtrip-4111.servers.mongodirector.com:27017/admin"));
        return uri;
    }
}
