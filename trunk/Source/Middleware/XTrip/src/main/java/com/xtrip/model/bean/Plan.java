package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author longnh
 */
public class Plan extends BaseBean {
	@JsonIgnore
	public static String COLLECTION_NAME = "plans";

	private String name;
	private String description;
	private long start;
	private long end;
	private Budget buget;
	private int member;
	private List<String> locationIds;
	private Map<String, Schedule> schedule;
	private List<String> visitedLocationsIds;
	private String ownerId;
	private byte type;
	private double distance;
	private String province;
	private Boolean isShared;
	private Boolean isPublic;
	private long dateCreated;
	private long dateModified;
	private List<String> likeIds;
	private List<String> commentIds;
	private List<String> ratingIds;
	private String note;

	public Plan() {
		name = "";
		description = "";
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
		buget = new Budget();
		member = 0;
		locationIds = new ArrayList<String>();
		schedule = new HashMap<String, Schedule>();
		visitedLocationsIds = new ArrayList<String>();
		ownerId = "";
		type = 0;
		distance = 0.0;
		province = "";
		isShared = true;
		isPublic = true;
		dateCreated = System.currentTimeMillis();
		dateModified = System.currentTimeMillis();
		likeIds = new ArrayList<String>();
		commentIds = new ArrayList<String>();
		ratingIds = new ArrayList<String>();
		note = "";
	}

	public String getId() {
		return _id.toString();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStart() {
		return this.start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return this.end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public Budget getBuget() {
		return this.buget;
	}

	public void setBuget(Budget buget) {
		this.buget = buget;
	}

	public int getMember() {
		return this.member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public List<String> getLocationIds() {
		return this.locationIds;
	}

	public void setLocationIds(List<String> locationIds) {
		this.locationIds = locationIds;
	}

	public Map<String, Schedule> getSchedule() {
		return this.schedule;
	}

	public void setSchedule(Map<String, Schedule> schedule) {
		this.schedule = schedule;
	}

	public List<String> getVisitedLocationsIds() {
		return this.visitedLocationsIds;
	}

	public void setVisitedLocationsIds(List<String> visitedLocationsIds) {
		this.visitedLocationsIds = visitedLocationsIds;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Boolean getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Boolean isShared) {
		this.isShared = isShared;
	}

	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public long getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(long dateModified) {
		this.dateModified = dateModified;
	}

	public List<String> getLikeIds() {
		return this.likeIds;
	}

	public void setLikeIds(List<String> likeIds) {
		this.likeIds = likeIds;
	}

	public List<String> getCommentIds() {
		return this.commentIds;
	}

	public void setCommentIds(List<String> commentIds) {
		this.commentIds = commentIds;
	}

	public List<String> getRatingIds() {
		return this.ratingIds;
	}

	public void setRatingIds(List<String> ratingIds) {
		this.ratingIds = ratingIds;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
