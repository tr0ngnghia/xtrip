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
import com.xtrip.common.Common.LocationType;

/**
 * @author longnh
 */
public class BLocation extends BaseBean {
	@JsonIgnore
	public static String COLLECTION_NAME = "locations";

	public String name = null;
	public String description;
	public LocationType type;
	public Double longtitude;
	public Double latitude;
	public List<String> imageUrls;
	public String planId;
	public Boolean isShared;
	public Boolean isPublic;
	public Date dateCreated;
	public Date dateModified;
	public List<String> likeId;
	public List<String> commentId;
	public List<String> ratingId;

	public BLocation() {
		super();
		this.likeId = new ArrayList<String>();
		this.commentId = new ArrayList<String>();
		this.ratingId = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	@JsonCreator
	public BLocation(Map<String, Object> props) {
		super(props);
		if (props == null)
			return;
		this.name = null;
		if (props.containsKey("_id"))
			this._id = (ObjectId) props.get("_id");

		if (props.containsKey("name"))
			this.name = (String) props.get("name");

		if (props.containsKey("description"))
			this.description = (String) props.get("description");

		if (props.containsKey("type"))
			this.type = (LocationType) props.get("type");

		if (props.containsKey("longtitude"))
			this.longtitude = (Double) props.get("longtitude");

		if (props.containsKey("latitude"))
			this.latitude = (Double) props.get("latitude");

		if (props.containsKey("imageUrls"))
			this.imageUrls = (ArrayList<String>) props.get("imageUrls");

		if (props.containsKey("planId"))
			this.planId = (String) props.get("planId");

		if (props.containsKey("isShared"))
			this.isShared = (Boolean) props.get("isShared");

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
	public Iterable<BLocation> getAllLocationId(String query, String projection) {
		return (Iterable<BLocation>) findWithProjection(query, projection);
	}

	/***********************************************/
	// STATIC METHODS
	/***********************************************/
	public static MongoCollection locations() {
		return model().all();
	}

	public static BLocation model() {
		return new BLocation();
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
