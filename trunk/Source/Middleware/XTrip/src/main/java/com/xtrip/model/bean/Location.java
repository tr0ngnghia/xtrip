package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.List;

import com.xtrip.common.Common.LocationType;

/**
 * @author longnh
 */
public class Location extends BaseBean {
	private String name;
	private String description;
	private LocationType type;
	private Double longtitude;
	private Double latitude;
	private List<String> imageUrls;
	private List<String> planIds;
	private Boolean isShared;
	private Boolean isPublic;
	private long dateCreated;
	private long dateModified;
	private List<String> likeIds;
	private List<String> commentIds;
	private List<String> ratingIds;

	public Location() {
		name = "";
		description = "";
		type = LocationType.DEFAULT;
		longtitude = 0.0;
		latitude = 0.0;
		imageUrls = new ArrayList<String>();
		planIds = new ArrayList<String>();
		isShared = true;
		isPublic = true;
		dateCreated = System.currentTimeMillis();
		dateModified = System.currentTimeMillis();
		likeIds = new ArrayList<String>();
		commentIds = new ArrayList<String>();
		ratingIds = new ArrayList<String>();
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

	public LocationType getType() {
		return this.type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}

	public Double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public List<String> getImageUrls() {
		return this.imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<String> getPlanIds() {
		return this.planIds;
	}

	public void setPlanIds(List<String> planIds) {
		this.planIds = planIds;
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
}
