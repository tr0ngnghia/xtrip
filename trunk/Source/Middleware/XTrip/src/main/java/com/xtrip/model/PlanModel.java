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
import com.xtrip.model.bean.Location;
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
			indexes.put("{name : 1, description: 1}", "{background: true, dropDups: true}");
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

	public Plan get(ObjectId _id) {
		return (Plan) all().findOne(_id).as(getClassInfo());
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
	public List<ObjectId> getAllPlanId() {
		List<ObjectId> res = new ArrayList<ObjectId>();
		Iterable<Plan> plans = (Iterable<Plan>) findWithProjection("{}",
				"{}");
		for (Plan plan : plans) {
			res.add(plan._id);

		}
		return res;
	}
}
