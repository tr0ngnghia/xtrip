package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longnh
 */
public class Location extends BaseBean {
	private String name;
	private String description;
	private String shortDesc;
	private long type;
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
	private String address;
	private String phone;
	private String fax;
	private String postCode;
	private String email;
	private String website;
	private int purchase;
	private long utils;
	private int capacity;
	private byte star;
	private int room;
	private float price;

	public Location() {
		name = "";
		description = "";
		shortDesc = "";
		type = 0;
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
		address = "";
		phone = "";
		fax = "";
		postCode = "0";
		email = "";
		website = "";
		purchase = 0;
		utils = 0;
		capacity = 0;
		star = '1';
		room = 0;
		price = 0;
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

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public long getType() {
		return this.type;
	}

	public void setType(long type) {
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public int getPurchase() {
		return this.purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	public long getUtils() {
		return this.utils;
	}

	public void setUtils(long utils) {
		this.utils = utils;
	}
	
	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public byte getStar() {
		return this.star;
	}

	public void setStar(byte star) {
		this.star = star;
	}
	
	public int getRoom() {
		return this.room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
	
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
