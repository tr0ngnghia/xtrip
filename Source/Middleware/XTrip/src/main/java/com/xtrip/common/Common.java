package com.xtrip.common;

/**
 * @author longnh
 */
public class Common {

	public enum LocationType {
		DEFAULT, HOTEL, RESTAURANT, SHOP, SUPERMARKET, BANK, TRAVEL_COMPANY, TRANSPORT, TRAVEL
	}

	public enum GenderType {
		MALE, FEMALE, LESBIAN, GAY, UNDEFINED
	}

	public enum RoleType {
		OWNER, MEMBER
	}

	public enum VehicleType {
		ON_FOOT, MOTOBIKE, BICYCLE, BUS, CAR, TRAIN, AIRPLANE, SPACECRAFT, SHIP, SUBMARINE
	}

	public enum CurrenncyType {
		USD, EUR, GBP, INR, AUD, CAD, SGD, CHF, MYR, JPY, CNY, NZD, THB, HUF, AED, HKD, MXN, ZAR, PHP, SEK, IDR, SAR, BRL, TRY, KES, KRW, EGP, IQD, NOK, KWD, RUB, DKK, PKR, ILS, PLN, QAR, XAU, OMR, COP, CLP, TWD, ARS, CZK, VND, MAD, JOD, BHD, XOF, LKR, UAH, NGN, TND, UGX, RON, BDT, PEN, GEL, XAF, FJD, VEF, BYR, HRK, UZS, BGN, DZD, IRR, DOP, ISK, XAG, CRC, SYP, LYD, JMD, MUR, GHS, AOA, UYU, AFN, LBP, XPF, TTD, TZS, ALL, XCD, GTQ, NPR, BOB, ZWD, BBD, CUC, LAK, BND, BWP, HNL, PYG, ETB, NAD, PGK, SDG, MOP, NIO, BMD, KZT, PAB, BAM, GYD, YER, MGA, KYD, MZN, RSD, SCR, AMD, SBD, AZN, SLL, TOP, BZD, MWK, GMD, BIF, SOS, HTG, GNF, MVR, MNT, CDF, STD, TJS, KPW, MMK, LSL, LRD, KGS, GIP, XPT, MDL, CUP, KHR, MKD, VUV, MRO, ANG, SZL, CVE, SRD, XPD, SVC, BSD, XDR, RWF, AWG, DJF, BTN, KMF, WST, SPL, ERN, FKP, SHP, JEP, TMT, TVD, IMP, GGP
	}

	public static final int ATM = 1; // 0001
	public static final int VISA = 2; // 0010
	public static final int CASH = 4; // 0100
	public static final int MASTERCARD = 8; // 1000
}
