package com.xtrip.model.bean;

import com.xtrip.common.Common.VehicleType;

/**
 * @author longnh
 */
public class PLocation extends BaseBean {

	private String locationId;
	private long start;
	private long end;
	private VehicleType vehicle;
	private Contact contact;
	private short order;
	private byte day;

	public PLocation() {
		locationId = "";
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
		vehicle = VehicleType.MOTOBIKE;
		contact = new Contact();
		day = 0;
	}
	
	public PLocation(byte day, String locationId) {
		this.locationId = locationId;
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
		vehicle = VehicleType.MOTOBIKE;
		contact = new Contact();
		this.day = day;
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

	public VehicleType getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(VehicleType vehicle) {
		this.vehicle = vehicle;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public short getOrder() {
		return this.order;
	}

	public void setOrder(short order) {
		this.order = order;
	}
	
	public byte getDay() {
		return this.day;
	}

	public void setDay(byte day) {
		this.day = day;
	}
}
