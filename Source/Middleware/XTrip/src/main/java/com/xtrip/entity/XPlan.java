package com.xtrip.entity;

import java.util.ArrayList;
import java.util.List;

public class XPlan {
	private String id;
	private String name;
	private String desc;
	private long start;
	private long end;
	private long dateCreated;
	private long dateModified;
	private float budget;
	private short member;
	private String owner;
	private byte type;
	private float distance;
	private String province;
	private String note;	
	private List<XDay> schedulers;
	private List<XLocation> locs;
	
	public XPlan() {
		id = "";
		name = "";
		desc = "";
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
		dateCreated = System.currentTimeMillis();
		dateModified = System.currentTimeMillis();
		budget = 0;
		member = 0;
		owner = "";
		type = 0;
		distance = 0;
		province = "";
		note = "";
		schedulers = new ArrayList<XDay>();
		locs = new ArrayList<XLocation>();
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

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public float getBudget() {
		return this.budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public List<XDay> getSchedulers() {
		return this.schedulers;
	}

	public void setSchedulers(List<XDay> scheduler) {
		if(scheduler != null){
			this.schedulers.clear();
			this.schedulers.addAll(scheduler);
		}		
	}

	public short getMember() {
		return this.member;
	}

	public void setMembers(short member) {
		this.member = member;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public float getDistance() {
		return this.distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public List<XLocation> getLocs() {
		return this.locs;
	}

	public void setLocs(List<XLocation> locs) {
		if(locs != null){
			this.locs.clear();
			this.locs.addAll(locs);
		}		
	}
}
