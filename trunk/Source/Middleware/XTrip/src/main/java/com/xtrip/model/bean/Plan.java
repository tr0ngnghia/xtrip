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

	public String name;
	public String description;
	public long start;
	public long end;
	public List<String> locationIds;
	public Map<String, String> members;
	public Map<String, String> vehicles;
	public Map<String, Double> budgets;
	public Map<String, String> contacts;
	public Map<String, String> visitedLocations;
	public List<String> userIds;
	public Boolean isShared;
	public Boolean isPublic;
	public long dateCreated;
	public long dateModified;
	public List<String> likeIds;
	public List<String> commentIds;
	public List<String> ratingIds;
	public String note;

	public Plan() {
		name = "";
		description = "";
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
		locationIds = new ArrayList<String>();
		members = new HashMap<String, String>();
		vehicles = new HashMap<String, String>();
		budgets = new HashMap<String, Double>();
		contacts = new HashMap<String, String>();
		visitedLocations = new HashMap<String, String>();
		userIds = new ArrayList<String>();
		isShared = true;
		isPublic = true;
		dateCreated = System.currentTimeMillis();
		dateModified = System.currentTimeMillis();
		likeIds = new ArrayList<String>();
		commentIds = new ArrayList<String>();
		ratingIds = new ArrayList<String>();
		note = "";
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

	public List<String> getLocationIds() {
		return this.locationIds;
	}

	public void setLocationIds(List<String> locationIds) {
		this.locationIds = locationIds;
	}

	public Map<String, String> getMembers() {
		return this.members;
	}

	public void setMembers(Map<String, String> members) {
		this.members = members;
	}

	public Map<String, String> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Map<String, String> vehicles) {
		this.vehicles = vehicles;
	}

	public Map<String, Double> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(Map<String, Double> budgets) {
		this.budgets = budgets;
	}

	public Map<String, String> getContacts() {
		return this.contacts;
	}

	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}

	public Map<String, String> getVisitedLocations() {
		return this.visitedLocations;
	}

	public void setVisitedLocations(Map<String, String> visitedLocations) {
		this.visitedLocations = visitedLocations;
	}

	public List<String> getUserIds() {
		return this.userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
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
