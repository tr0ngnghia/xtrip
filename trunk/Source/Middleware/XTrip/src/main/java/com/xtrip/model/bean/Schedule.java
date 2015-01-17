package com.xtrip.model.bean;

import com.xtrip.common.Common.VehicleType;

/**
 * @author longnh
 */
public class Schedule extends BaseBean {

	private long start;
	private long end;
	private VehicleType vehicle;
	private Contact contact;

	public Schedule() {
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
		vehicle = VehicleType.MOTOBIKE;
		contact = new Contact();
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
}
