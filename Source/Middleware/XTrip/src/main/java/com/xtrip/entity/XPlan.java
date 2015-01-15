package com.xtrip.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xtrip.common.Common.VehicleType;
import com.xtrip.model.bean.Budget;
import com.xtrip.model.bean.Contact;
import com.xtrip.model.bean.Day;
import com.xtrip.model.bean.Schedule;

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
	private short length;
	private String province;
	private String note;
	private List<XLocation> locations;
	private List<Day> schedules;
	
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
		length = 0;
		province = "";
		note = "";
		locations = new ArrayList<XLocation>();
		schedules = new ArrayList<Day>();
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

	public List<XLocation> getLocations() {
		return this.locations;
	}

	public void setLocations(List<XLocation> locations) {
		if(locations != null){
			this.locations.addAll(locations);
		}		
	}

	public List<Day> getSchedules() {
		return this.schedules;
	}

	public void setSchedules(List<Day> schedules) {
		if(schedules != null){
			this.schedules.addAll(schedules);
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
	
	public short getLength() {
		return this.length;
	}

	public void setLength(short length) {
		this.length = length;
	}
	
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}
