package com.xtrip.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xtrip.model.bean.Region;

public class RegionModel extends BaseModel {
	@JsonIgnore
	public static String COLLECTION_NAME = "region";

	private static RegionModel instance = null;

	private RegionModel() {
		super();
	}

	public static RegionModel getInstance() {
		if (instance == null) {
			instance = new RegionModel();
		}
		return instance;
	}

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public Class<?> getClassInfo() {
		return Region.class;
	}

	public MongoCollection locations() {
		return all();
	}

	public boolean set(Region region) {
		String error = null;
		if (region._id == null)
			error = all().save(region).getError();
		else
			error = all().update(region._id).with(region).getError();
		return error == null || "".equals(error);
	}

	public Region get(ObjectId _id) {
		return (Region) all().findOne(_id).as(getClassInfo());
	}

	public List<Region> multiGet(List<ObjectId> _ids) {
		List<Region> res = new ArrayList<Region>();
		Iterable<Region> regions = (Iterable<Region>) all().find(
				"{_id:{$in:#}}", _ids).as(Region.class);
		for (Region region : regions) {
			res.add(region);
		}
		return res;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ObjectId> getIds(Byte level, String parent) {
		String query = "";
		if(level != null){
			query = query + "level: " + level + ",";
		}
		if(parent != null && !parent.isEmpty()){
			query = query + "parent: " + "\"" + parent + "\"";
		}
		
		List<ObjectId> res = new ArrayList<ObjectId>();
		Iterable<Region> regions = (Iterable<Region>) findWithProjection("{"+query+"}", "{_id : 1}");
		for (Region region : regions) {
			res.add(region._id);
		}
		return res;
	}

	@Override
	@JsonIgnore
	public void ensureIndexes(Map<String, String> indexes) {
		if (indexes == null) {
			indexes = new HashMap<String, String>();
			indexes.put("{name : 1, parent: 1}",
					"{background: true, dropDups: true}");
		}
		this.createIndexes(indexes);
	}
}