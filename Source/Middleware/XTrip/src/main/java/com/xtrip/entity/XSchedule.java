package com.xtrip.entity;


public class XSchedule {
	private String locationId;
	private long start;
	private long end;
	private short order;
	
	public XSchedule() {
		locationId = "";
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
	}
	
	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	
	public short getOrder() {
		return this.order;
	}

	public void setOrder(short order) {
		this.order = order;
	}
}
