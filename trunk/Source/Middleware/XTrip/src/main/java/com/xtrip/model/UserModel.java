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
import com.xtrip.model.bean.User;

/**
 * @author longnh
 */
public class UserModel extends BaseModel {
	@JsonIgnore
	public static String COLLECTION_NAME = "users";

	private static UserModel instance = null;

	private UserModel() {
		super();
	}

	public static UserModel getInstance() {
		if (instance == null) {
			instance = new UserModel();
		}
		return instance;
	}

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public Class<?> getClassInfo() {
		return User.class;
	}

	public MongoCollection locations() {
		return all();
	}

	@Override
	@JsonIgnore
	public void ensureIndexes(Map<String, String> indexes) {
		if (indexes == null) {
			indexes = new HashMap<String, String>();
			indexes.put("{username : 1}", "{background: true, dropDups: true}");
		}
		this.createIndexes(indexes);
	}

	public boolean set(User user) {
		String error = null;
		if (user._id == null)
			error = all().save(user).getError();
		else
			error = all().update(user._id).with(user).getError();
		return error == null || "".equals(error);
	}

	public User get(ObjectId _id) {
		return (User) all().findOne(_id).as(getClassInfo());
	}

	public List<User> multiGet(List<ObjectId> _ids) {
		List<User> res = new ArrayList<User>();
		Iterable<User> users = (Iterable<User>) all().find("{_id:{$in:#}}",
				_ids).as(User.class);
		for (User user : users) {
			res.add(user);
		}
		return res;
	}

	public Integer getTotalNumberOfUser() {
		return all().aggregate("{$group : {_id : 0, count : {$sum : 1}}}")
				.map(new ResultHandler<Integer>() {

					@Override
					public Integer map(DBObject arg0) {
						return (Integer) arg0.get("count");
					}
				}).get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ObjectId> getAllUserIds() {
		List<ObjectId> res = new ArrayList<ObjectId>();
		Iterable<User> users = (Iterable<User>) findWithProjection("{}",
				"{_id : 1}");
		for (User user : users) {
			res.add(user._id);
		}
		return res;
	}
}
