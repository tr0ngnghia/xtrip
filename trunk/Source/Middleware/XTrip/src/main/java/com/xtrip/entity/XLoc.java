package com.xtrip.entity;

public class XLoc {
	private short order;
	private String id;
	private boolean visited;
	private String vehicle;
//	private String note;
	
	public XLoc(){		
	}
	
	public XLoc(short order, String id){
		this.order = order;
		this.id = id;
		this.visited = false;
		this.vehicle = "";
//		this.note = "";
	}		
	
	public short getOrder(){
		return order;	
	}
	
	public void setOrder(short order){
		this.order = order;	
	}
	
	public String getId(){
		return id;	
	}
	
	public void setId(String id){
		this.id = id;	
	}
	
	public boolean getVisited(){
		return visited;	
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;	
	}
	
	public String getVehicle(){
		return vehicle;	
	}
	
	public void setVehicle(String vehicle){
		this.vehicle = vehicle;	
	}
}
