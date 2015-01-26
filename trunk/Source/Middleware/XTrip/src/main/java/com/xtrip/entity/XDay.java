package com.xtrip.entity;

import java.util.ArrayList;
import java.util.List;

public class XDay {
	private int order;
	private List<XLoc> pLocs;
	
	public XDay(){		
	}
	
	public XDay(int index){
		this.order = index;
		pLocs = new ArrayList<XLoc>();
	}
	
	public int getOrder(){
		return order;
	}
	
	public void setOrder(int order){
		this.order = order;
	}
	
	public List<XLoc> getPLocs(){
		List<XLoc> temp = new ArrayList<XLoc>();
		temp.addAll(this.pLocs);
		return temp;
	}
	
	public void setPLocs(List<XLoc> locations){
		if(locations != null){
			this.pLocs.addAll(locations);	
		}		
	}
}
