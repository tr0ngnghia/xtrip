package com.xtrip.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.ResultHandler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.DBObject;
import com.xtrip.model.bean.Plan;

/**
 * @author longnh
 */
public class PlanModel extends BaseModel {
	@JsonIgnore
	public static String COLLECTION_NAME = "plans";

	private static PlanModel instance = null;

	private PlanModel() {
		super();
	}

	public static PlanModel getInstance() {
		if (instance == null) {
			instance = new PlanModel();
		}
		return instance;
	}

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public Class<?> getClassInfo() {
		return Plan.class;
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

	public boolean set(Plan plan) {
		String error = null;
		if (plan._id == null)
			error = all().save(plan).getError();
		else
			error = all().update(plan._id).with(plan).getError();
		return error == null || "".equals(error);
	}

	public Plan get(String id) {
		String query = "{id: " + "\"" + (id != null ? id : "") + "\"" + "}";
		return (Plan) all().findOne(query).as(getClassInfo());
	}

	public List<Plan> multiGet(List<ObjectId> _ids) {
		List<Plan> res = new ArrayList<Plan>();
		Iterable<Plan> plans = (Iterable<Plan>) all().find("{_id:{$in:#}}",
				_ids).as(Plan.class);
		for (Plan plan : plans) {
			res.add(plan);
		}
		return res;
	}

	public Integer getTotalNumberOfPlan() {
		return all().aggregate("{$group : {_id : 0, count : {$sum : 1}}}")
				.map(new ResultHandler<Integer>() {

					@Override
					public Integer map(DBObject arg0) {
						return (Integer) arg0.get("count");
					}
				}).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectId> getAllPlanIds() {
		List<ObjectId> res = new ArrayList<ObjectId>();
		Iterable<Plan> plans = (Iterable<Plan>) findWithProjection("{}",
				"{_id : 1}");
		for (Plan plan : plans) {
			res.add(plan._id);
		}
		return res;
	}
	
	public void remove(String id){
		String query = "{id: " + "\"" + (id != null ? id : "") + "\"" + "}";
		all().remove(query);
	}
}
