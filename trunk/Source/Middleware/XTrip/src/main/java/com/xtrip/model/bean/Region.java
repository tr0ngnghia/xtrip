package com.xtrip.model.bean;


public class Region extends BaseBean {
	private String postcode;
	private String name;
	private String type;
	private byte level;
	private String parent;
	

	public Region() {
		name = "";
		type = "";
		level = 0;
		parent = "";
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
	
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public byte getLevel() {
		return this.level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}	
}