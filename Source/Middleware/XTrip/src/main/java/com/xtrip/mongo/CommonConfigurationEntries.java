package com.xtrip.mongo;

/**
 * @author longnh
 */
public enum CommonConfigurationEntries implements IConfigurationEntries{	
	mongo_uri("mongo.uri"),
	mongo_gridfs_enabled("mongo.gridfs.enabled"),
	application_is_test("application.isTest");
	
	
	private String value;
	
	CommonConfigurationEntries(String val){
		this.value = val;
	}					
	
	public String getValue(){
		return this.value;
	}
}
