package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xtrip.common.Common.VehicleType;

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
	private long totalBuget;
	private List<String> locationIds;
	private Map<String, Schedule> scheduleOfLocation;
	private Map<String, Integer> memberOfLocation;
	private Map<String, VehicleType> vehicleOfLocations;
	private Map<String, Budget> budgetOfLocations;
	private Map<String, Contact> contactOfLocations;
	private List<String> visitedLocationsIds;
	private String ownerId;
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
		totalBuget = 0;
		locationIds = new ArrayList<String>();
		scheduleOfLocation = new HashMap<String, Schedule>();
		memberOfLocation = new HashMap<String, Integer>();
		vehicleOfLocations = new HashMap<String, VehicleType>();
		budgetOfLocations = new HashMap<String, Budget>();
		contactOfLocations = new HashMap<String, Contact>();
		visitedLocationsIds = new ArrayList<String>();
		ownerId = "";
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

	public long getTotalBuget() {
		return this.totalBuget;
	}

	public void setTotalBuget(long totalBuget) {
		this.totalBuget = totalBuget;
	}

	public List<String> getLocationIds() {
		return this.locationIds;
	}

	public void setLocationIds(List<String> locationIds) {
		this.locationIds = locationIds;
	}

	public Map<String, Schedule> getScheduleOfLocation() {
		return this.scheduleOfLocation;
	}

	public void setScheduleOfLocation(Map<String, Schedule> scheduleOfLocation) {
		this.scheduleOfLocation = scheduleOfLocation;
	}

	public Map<String, Integer> getMemberOfLocation() {
		return this.memberOfLocation;
	}

	public void setMemberOfLocation(Map<String, Integer> memberOfLocation) {
		this.memberOfLocation = memberOfLocation;
	}

	public Map<String, VehicleType> getVehicleOfLocations() {
		return this.vehicleOfLocations;
	}

	public void setVehicleOfLocations(
			Map<String, VehicleType> vehicleOfLocations) {
		this.vehicleOfLocations = vehicleOfLocations;
	}

	public Map<String, Budget> getBudgetOfLocations() {
		return this.budgetOfLocations;
	}

	public void setBudgetOfLocations(Map<String, Budget> budgetOfLocations) {
		this.budgetOfLocations = budgetOfLocations;
	}

	public Map<String, Contact> getContactOfLocations() {
		return this.contactOfLocations;
	}

	public void setContactOfLocations(Map<String, Contact> contactOfLocations) {
		this.contactOfLocations = contactOfLocations;
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
