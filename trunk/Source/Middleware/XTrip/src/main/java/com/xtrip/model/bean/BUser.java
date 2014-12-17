package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.ResultHandler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.DBObject;
import com.xtrip.common.Common.RoleType;

/**
 * @author longnh
 */
public class BUser extends BaseBean {
	@JsonIgnore
	public static String COLLECTION_NAME = "users";

	public String username = null;
	public String password;
	public Map<RoleType, String> roleOfPlan;
	public Date age;
	public String email;
	public String nationality;
	public List<String> favouriteLocationId;
	public Date dateCreated;
	public Date dateModified;
	public List<String> likeId;
	public List<String> commentId;
	public List<String> ratingId;

	public BUser() {
		super();
		this.roleOfPlan = new HashMap<RoleType, String>();
		this.likeId = new ArrayList<String>();
		this.commentId = new ArrayList<String>();
		this.ratingId = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	@JsonCreator
	public BUser(Map<String, Object> props) {
		super(props);
		if (props == null)
			return;
		this.username = null;
		if (props.containsKey("_id"))
			this._id = (ObjectId) props.get("_id");

		if (props.containsKey("username"))
			this.username = (String) props.get("username");

		if (props.containsKey("password"))
			this.password = (String) props.get("password");

		if (props.containsKey("roleOfPlan"))
			this.roleOfPlan = (Map<RoleType, String>) props.get("roleOfPlan");

		if (props.containsKey("age"))
			this.age = (Date) props.get("age");

		if (props.containsKey("email"))
			this.email = (String) props.get("email");

		if (props.containsKey("nationality"))
			this.nationality = (String) props.get("nationality");

		if (props.containsKey("favouriteLocationId"))
			this.favouriteLocationId = (ArrayList<String>) props
					.get("favouriteLocationId");

		if (props.containsKey("dateCreated"))
			this.dateCreated = (Date) props.get("dateCreated");

		if (props.containsKey("dateModified"))
			this.dateModified = (Date) props.get("dateModified");

		if (props.containsKey("likeId"))
			this.likeId = (ArrayList<String>) props.get("likeId");

		if (props.containsKey("commentId"))
			this.commentId = (ArrayList<String>) props.get("commentId");

		if (props.containsKey("ratingId"))
			this.ratingId = (ArrayList<String>) props.get("ratingId");
	}

	/***********************************************/
	// METHODS
	/***********************************************/

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public Class<?> getClassInfo() {
		return this.getClass();
	}

	@Override
	@JsonIgnore
	public void ensureIndexes(Map<String, String> indexes) {
		if (indexes == null) {
			indexes = new HashMap<String, String>();
			indexes.put("{username : 1}",
					"{background: true, username : 'username', dropDups: true}");
		}
		this.createIndexes(indexes);
	}

	@SuppressWarnings("unchecked")
	public Iterable<BUser> getAllLocationId(String query, String projection) {
		return (Iterable<BUser>) findWithProjection(query, projection);
	}

	/***********************************************/
	// STATIC METHODS
	/***********************************************/
	public static MongoCollection locations() {
		return model().all();
	}

	public static BUser model() {
		return new BUser();
	}

	public static Integer getTotalNumberOfLocation() {
		return model().all()
				.aggregate("{$group : {_id : 0, count : {$sum : 1}}}")
				.map(new ResultHandler<Integer>() {

					@Override
					public Integer map(DBObject arg0) {
						return (Integer) arg0.get("count");
					}
				}).get(0);
	}
}
