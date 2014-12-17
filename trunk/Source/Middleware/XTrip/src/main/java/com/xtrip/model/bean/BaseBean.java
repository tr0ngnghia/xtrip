package com.xtrip.model.bean;

import java.util.Map;
import java.util.Map.Entry;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xtrip.model.connectors.IMongoDBConnector;


/**
 * @author longnh
 */
public abstract class BaseBean{
	@JsonIgnore	
	public static IMongoDBConnector connector = null;
	@JsonIgnore
	private boolean isModel = false;
	
	public ObjectId _id;
	
	public BaseBean(Map<String, Object> props) {
		if (props.containsKey("_id"))
			this._id = (ObjectId) props.get("_id");
	}
	public BaseBean() {
		// TODO Auto-generated constructor stub
	}
	@JsonIgnore
	public abstract Class<?> getClassInfo();
	@JsonIgnore
    public abstract String getCollectionName();
	@JsonIgnore
	public abstract void ensureIndexes(Map<String, String> indexes);
    
    public MongoCollection all(){    	    	
		if (connector != null){
    		return connector.getCollection(getCollectionName());
    	}		
    	return null;
    }
    
    public boolean save(){
    	String error = null;
        if (this._id == null)
        	error = all().save(this).getError(); 
        else
        	error = all().update(this._id).with(this).getError();
        return error == null || "".equals(error);
    }

    public void remove(){    	
        all().remove(this._id);
    }
         
    
    public Object findByPK(ObjectId _id){    	
    	return all().findOne(_id).as(getClassInfo());
    }
    
    public Object findOne(String query){    	
    	return all().findOne(query).as(getClassInfo());
    }
    
    public Object find(String query){
    	return all().find(query).as(getClassInfo());
    }
    
    public Object findWithProjection(String query, String projection){
    	return all().find(query).projection(projection).as(getClassInfo());
    }
    
    
	protected void createIndexes(Map<String, String> indexes) {
		if (indexes == null) return;
		MongoCollection collection = all();
		for(Entry<String, String> entry : indexes.entrySet()){			
				collection.ensureIndex(entry.getKey(), entry.getValue());			
		}
		
	}
	
	public String toJsonString() throws JsonProcessingException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(this);
		return json;
	}
       
}
