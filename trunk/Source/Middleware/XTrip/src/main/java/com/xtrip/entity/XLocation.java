package com.xtrip.entity;

import java.util.ArrayList;
import java.util.List;

import com.xtrip.common.Common.LocationType;

public class XLocation {
	private String id;
	private String name;
	private String shortDesc;
	private String longDesc;
	private LocationType type;
	private Double lng;
	private Double lat;
	private String avatar;
	private List<String> galary;
	private Boolean isShared;
	private Boolean isPublic;
	private long dateCreated;
	private long dateModified;	
	private int rating;
	private int visit;
	private float price;
	private String address;
	private String phone;
	private String fax;
	private int postCode;

	public XLocation() {
		id = "";
		name = "";
		shortDesc = "";
		longDesc = "";
		type = LocationType.DEFAULT;
		lng = 0.0;
		lat = 0.0;
		avatar = "";
		galary = new ArrayList<String>();
		isShared = true;
		isPublic = true;
		dateCreated = System.currentTimeMillis();
		dateModified = System.currentTimeMillis();
		rating = 0;
		visit = 0;
		price = 0;
		address = "";
		phone = "";
		fax = "";
		postCode = 0;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	
	public String getLongDesc() {
		return this.longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public LocationType getType() {
		return this.type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}

	public Double getLng() {
		return this.lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return this.lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public List<String> getGalary() {
		return this.galary;
	}

	public void setGalary(List<String> imageUrls) {
		this.galary = imageUrls;
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
	
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getVisit() {
		return this.visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}
	
	public float getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	public int getPostCode() {
		return this.postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
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
}
