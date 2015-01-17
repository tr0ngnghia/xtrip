package com.xtrip.entity;

import java.util.ArrayList;
import java.util.List;

public class XDay {
	private int index;
	private List<XLoc> locations;
	
	public XDay(){		
	}
	
	public XDay(int index){
		this.index = index;
		locations = new ArrayList<XLoc>();
		XLoc loc1 = new XLoc(1, "54b2b8f60fa18d106e0398ce");
		XLoc loc2 = new XLoc(2, "54b2b8f60fa18d106e0398d6");
		locations.add(loc1);
		locations.add(loc2);
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public List<XLoc> getLocations(){
		List<XLoc> temp = new ArrayList<XLoc>();
		temp.addAll(this.locations);
		return temp;
	}
	
	public void setLocations(List<XLoc> locations){
		if(locations != null){
			this.locations.addAll(locations);	
		}		
	}
}
