package com.xtrip.model.bean;


/**
 * @author longnh
 */
public class Contact extends BaseBean {

	private String name;
	private String mobilePhone;	

	public Contact() {
		name = "";
		mobilePhone = "";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
