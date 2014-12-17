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

/**
 * @author longnh
 */
public class BPlan extends BaseBean {
	@JsonIgnore
	public static String COLLECTION_NAME = "plans";

	public String name = null;
	public String description;
	public Date start;
	public Date end;
	public List<String> locationId;
	public Integer noMember;
	public Map<String, String> vehicle;
	public Integer budget;
	public String contact;
	public Integer visited;
	public List<String> userId;
	public Boolean isPublic;
	public Date dateCreated;
	public Date dateModified;
	public List<String> likeId;
	public List<String> commentId;
	public List<String> ratingId;
	public String note;

	public BPlan() {
		super();
		this.vehicle = new HashMap<String, String>();
		this.likeId = new ArrayList<String>();
		this.commentId = new ArrayList<String>();
		this.ratingId = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	@JsonCreator
	public BPlan(Map<String, Object> props) {
		super(props);
		if (props == null)
			return;
		this.name = null;
		if (props.containsKey("_id"))
			this._id = (ObjectId) props.get("_id");

		if (props.containsKey("name"))
			this.name = (String) props.get("name");

		if (props.containsKey("start"))
			this.start = (Date) props.get("start");

		if (props.containsKey("end"))
			this.end = (Date) props.get("end");

		if (props.containsKey("locationId"))
			this.locationId = (ArrayList<String>) props.get("locationId");

		if (props.containsKey("noMember"))
			this.noMember = (Integer) props.get("noMember");

		if (props.containsKey("vehicle"))
			this.vehicle = (HashMap<String, String>) props.get("vehicle");

		if (props.containsKey("budget"))
			this.budget = (Integer) props.get("budget");

		if (props.containsKey("userId"))
			this.userId = (ArrayList<String>) props.get("userId");

		if (props.containsKey("isPublic"))
			this.isPublic = (Boolean) props.get("isPublic");

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

		if (props.containsKey("note"))
			this.note = (String) props.get("note");
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
			indexes.put("{name : 1}",
					"{background: true, name : 'name', dropDups: true}");
		}
		this.createIndexes(indexes);
	}

	@SuppressWarnings("unchecked")
	public Iterable<BPlan> getAllLocationId(String query, String projection) {
		return (Iterable<BPlan>) findWithProjection(query, projection);
	}

	/***********************************************/
	// STATIC METHODS
	/***********************************************/
	public static MongoCollection locations() {
		return model().all();
	}

	public static BPlan model() {
		return new BPlan();
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
