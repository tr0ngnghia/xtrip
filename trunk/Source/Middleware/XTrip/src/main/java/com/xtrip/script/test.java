package com.xtrip.script;

import java.util.List;

import org.bson.types.ObjectId;

import com.xtrip.model.LocationModel;
import com.xtrip.model.bean.Location;

public class test extends BasicTask {
	public static void main(String[] args) {
		test instance = new test();
		instance.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Location location = new Location();
		// location._id = new ObjectId("549c0cd9e160ed69abe34767");
		location.setDescription("this is new 2");
		LocationModel.getInstance().set(location); 
		LocationModel.getInstance().remove(new ObjectId("549c1157e160914d721bed21"));
		List<ObjectId> objectIds = LocationModel.getInstance().getAllLocationId("{}", "{}");
		for(Object objectId : objectIds) {
			System.out.println(objectId.toString());
		}
		System.out.println(LocationModel.getInstance().getTotalNumberOfLocation());
		
		Location res = LocationModel.getInstance().get(new ObjectId("549c0cd9e160ed69abe34767"));
		System.out.println(res.getDescription());
		System.out.println("DONE");
	}
}
