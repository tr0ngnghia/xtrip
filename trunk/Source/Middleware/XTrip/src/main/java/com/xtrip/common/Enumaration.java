package com.xtrip.common;

import java.util.Arrays;
import java.util.List;

public class Enumaration {
	
	// enum for location, store by 64 bit of long, each bit present a type of location, a mix location stored by using more than 1 bit
	public static List<String> LOCATION_TYPE = Arrays.asList(new String[]{
			"Hotel","Resort","Motel","Restaurant","Cafe","FoodStall","Tower","Pagoda",
			"Church","Park","ATM","Bank","Taxi","Committee","Souvenir","GasStation",
			"Pharmacy","Island","Peninsula","Bar","Museum","Supermarket","Market","Harbor",
			"TrainStation","CarStation","BusStation","Hospital","Karaoke","Massage","Spa","PublicToilet",
			"Moutain","River","Beach","BookStore","Library","TeaRoom","Pass","Cinema",
			"Lake","Theatre","Forest","IceCream","Clinic","TouristArea","Mall","VehiclesRental",
			"Shop","TravelCompany","Transport"
//			"more","more","more","more","more","more","more","more",
//			"more","more","more","more","more","more","more","more",
	});
	
	public static List<Long> LIVE_LOCATION = Arrays.asList(new Long[]{1l,2l,4l});
	public static List<Long> EAT_LOCATION = Arrays.asList(new Long[]{8l,16l,32l});
	
	// enum for purchase type
	public static List<String> PURCHASE_TYPE = Arrays.asList(new String[]{
			"ATM","VISA","CASH","MASTERCARD"
	});
	
	// enum for utils of location
	public static List<String> UTILS_TYPE = Arrays.asList(new String[]{
			"Reservation","DeliveryServices","TakeawayServices","Wifi free","Playground for kids","Outdoor seats","Private room","Air Conditioner",
			"Free bike park","Tips for staff","Car park","Smoking zone","Member cards","Tax invoice available","Seminar Support","Heat Conditioner",			
			"Disabled person support","Live sport tv","Live music","DJ Music","Party support","laundry services","Booking plane service","Change money services",
			"shuttle service",
	});
	
		
	
	
	
	
	
	
	
	
	
	

}
