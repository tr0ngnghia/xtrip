package com.xtrip.common;


/**
 * @author longnh
 */
public class Common {

	public enum LocationType {
		DEFAULT, HOTEL, RESTAURANT, SHOP, SUPERMARKET, BANK, TRAVEL_COMPANY, TRANSPORT, TRAVEL
	}	

	public static final int ATM = 1; // 0001
	public static final int VISA = 2; // 0010
	public static final int CASH = 4; // 0100
	public static final int MASTERCARD = 8; // 1000

	public enum RoleType {
		LEADER, MEMBER
	}
}
