package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xtrip.common.Common.GenderType;
import com.xtrip.common.Common.RoleType;

/**
 * @author longnh
 */
public class User extends BaseBean {
	@JsonIgnore
	public static String COLLECTION_NAME = "users";

	private String username;
	private String password;
	private int age;
	private String phone;
	private String mobilePhone;
	private String email;
	private String address;
	private String nationality;
	private long birthday;
	private GenderType gender;
	private long dateCreated;
	private long dateModified;
	private List<String> planIds;
	private Map<String, RoleType> roleOfPlans;
	private List<String> likeIds;
	private List<String> commentIds;
	private List<String> ratingIds;
	

	public User() {
		username = "";
		password = "";
		age = 0;
		phone = "";
		mobilePhone = "";
		email = "";
		address = "";
		nationality = "";
		birthday = 0;
		gender = GenderType.MALE;
		dateCreated = System.currentTimeMillis();
		dateModified = System.currentTimeMillis();
		planIds = new ArrayList<String>();
		roleOfPlans = new HashMap<String, RoleType>();
		likeIds = new ArrayList<String>();
		commentIds = new ArrayList<String>();
		ratingIds = new ArrayList<String>();
	}
	
	public String getId() {
		return _id.toString();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public long getBirthday() {
		return this.birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public GenderType getGender() {
		return this.gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public Map<String, RoleType> getRoleOfPlans() {
		return this.roleOfPlans;
	}

	public void setRoleOfPlans(Map<String, RoleType> roleOfPlans) {
		this.roleOfPlans = roleOfPlans;
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
	
	public List<String> getPlanIds() {
		return this.planIds;
	}

	public void setPlanIds(List<String> planIds) {
		this.planIds = planIds;
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
