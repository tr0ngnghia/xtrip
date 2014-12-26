package com.xtrip.script;

import java.util.List;

import org.bson.types.ObjectId;

import com.xtrip.model.LocationModel;
import com.xtrip.model.bean.Location;

public class TestModel extends BasicTask {
	public static void main(String[] args) {
		TestModel instance = new TestModel();
		instance.run();
	}

	@Override
	public void run() {
		//----------------- Set method -----------------
		Location location = new Location();
		location.setDescription("this is new 2");
		LocationModel.getInstance().set(location);
		
		//----------------- Remove method -----------------
		LocationModel.getInstance().remove(new ObjectId("549c1157e160914d721bed21"));
		
		//----------------- Get All Location Id method -----------------
		List<ObjectId> objectIds = LocationModel.getInstance().getAllLocationId();
		for(Object objectId : objectIds) {
			System.out.println(objectId.toString());
		}
		
		//----------------- Get Total Number Of Location method -----------------
		System.out.println(LocationModel.getInstance().getTotalNumberOfLocation());
		
		//----------------- Get method -----------------
		Location res = LocationModel.getInstance().get(new ObjectId("549c0cd9e160ed69abe34767"));
		System.out.println(res.getDescription());
		
		
		System.out.println("DONE");
	}
}
