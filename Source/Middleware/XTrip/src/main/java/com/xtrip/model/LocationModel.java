package com.xtrip.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xtrip.model.bean.Location;

/**
 * @author longnh
 */
public class LocationModel extends BaseModel {
	@JsonIgnore
	public static String COLLECTION_NAME = "locations";

	private static LocationModel instance = null;

	private LocationModel() {
		super();
	}

	public static LocationModel getInstance() {
		if (instance == null) {
			instance = new LocationModel();
		}
		return instance;
	}

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public Class<?> getClassInfo() {
		return Location.class;
	}

	public MongoCollection locations() {
		return all();
	}

	@Override
	@JsonIgnore
	public void ensureIndexes(Map<String, String> indexes) {
		if (indexes == null) {
			indexes = new HashMap<String, String>();
			indexes.put("{name : 1, description: 1}",
					"{background: true, dropDups: true}");
		}
		this.createIndexes(indexes);
	}

	public boolean set(Location location) {
		String error = null;
		if (location._id == null)
			error = all().save(location).getError();
		else
			error = all().update(location._id).with(location).getError();
		return error == null || "".equals(error);
	}

	public Location get(ObjectId _id) {
		return (Location) all().findOne(_id).as(getClassInfo());
	}

	public List<Location> multiGet(List<ObjectId> _ids) {
		List<Location> res = new ArrayList<Location>();
		Iterable<Location> locations = (Iterable<Location>) all().find(
				"{_id:{$in:#}}", _ids).as(Location.class);
		for (Location location : locations) {
			res.add(location);
		}
		return res;
	}

	public Long getTotalNumberOfLocation(Long type, String postCode, String name) {
		String query = "";
		if (type != null) {
			query = query + "type: " + type + ",";
		}
		if (postCode != null && !postCode.isEmpty()) {
			query = query + "postCode: " + "\"" + postCode + "\"" + ",";;
		}
		if(name != null && !name.isEmpty()){
			query = query + "name: " + "/" + name + "/";
		}
		return all().count("{" + query + "}");
		// cai cho aggregate nay nghia them condition ko dc, long xem viet lai
		// giup nghia nhe.
		// Trong tai lieu mongo noi la count bang aggregate se chinh xac hon la
		// bang count thong thuong.
		// return
		// all().aggregate("[{ $match: {"+query+"}}, {$group : {_id : 0, count : {$sum : 1}}}]")
		// .map(new ResultHandler<Integer>() {
		//
		// @Override
		// public Integer map(DBObject arg0) {
		// System.out.println(arg0.toString());
		// return (Integer) arg0.get("count");
		// }
		// }).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectId> getAllLocationIds() {
		List<ObjectId> res = new ArrayList<ObjectId>();
		Iterable<Location> locations = (Iterable<Location>) findWithProjection(
				"{}", "{_id : 1}");
		for (Location location : locations) {
			res.add(location._id);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<ObjectId> getSliceLocationIds(Long type, String postCode, String name) {
		String query = "";
		if (type != null) {
			query = query + "type: " + type + ",";
		}
		if (postCode != null && !postCode.isEmpty() && postCode.length() == 10) {
			query = query + "postCode: " + "\"" + postCode + "\"" + ",";
		}
		if(name != null && !name.isEmpty()){
			query = query + "name: " + "{$regex:/" + name + "/}";
		}

		List<ObjectId> res = new ArrayList<ObjectId>();
		Iterable<Location> locations = (Iterable<Location>) findWithProjection("{" + query + "}", "{_id : 1}");
		for (Location location : locations) {
			res.add(location._id);
		}
		return res;
	}
}
