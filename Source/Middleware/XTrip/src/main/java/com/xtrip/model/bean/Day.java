package com.xtrip.model.bean;

import java.util.ArrayList;
import java.util.List;

public class Day {
	private byte index;
	private List<Day.Location> locations;
	
	public Day(byte index){
		this.index = index;
		locations = new ArrayList<Day.Location>();
	}
	
	public byte getIndex(){
		return index;
	}
	
	public void setIndex(byte index){
		this.index = index;
	}
	
	public List<Day.Location> getLocations(){
		List<Day.Location> temp = new ArrayList<Day.Location>();
		temp.addAll(this.locations);
		return temp;
	}
	
	public void setLocations(List<Day.Location> locations){
		if(locations != null){
			this.locations.addAll(locations);	
		}		
	}
	
	class Location{
		private byte order;
		private String id;
		private boolean visited;
		private String vehicle;
//		private String note;
		
		public Location(byte order, String id){
			this.order = order;
			this.id = id;
			this.visited = false;
			this.vehicle = "";
//			this.note = "";
		}		
	}
}
