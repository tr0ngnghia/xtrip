package com.xtrip.test;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.xtrip.model.PlanModel;
import com.xtrip.model.bean.Plan;
import com.xtrip.model.bean.Schedule;
import com.xtrip.script.BasicTask;

public class TestModel extends BasicTask {
	public static void main(String[] args) {
		TestModel instance = new TestModel();
		instance.run();
	}

	@Override
	public void run() {
		// //----------------- Set method -----------------
		// Location location = new Location();
		// location.setDescription("this is new 2");
		// LocationModel.getInstance().set(location);
		//
		// //----------------- Remove method -----------------
		// LocationModel.getInstance().remove(new
		// ObjectId("549c1157e160914d721bed21"));
		//
		// //----------------- Get All Location Id method -----------------
		// List<ObjectId> objectIds =
		// LocationModel.getInstance().getAllLocationIds();
		// for(Object objectId : objectIds) {
		// System.out.println(objectId.toString());
		// }
		//
		// //----------------- Get Total Number Of Location method
		// -----------------
		// System.out.println(LocationModel.getInstance().getTotalNumberOfLocation(null,
		// null));
		//
		// //----------------- Get method -----------------
		// Location res = LocationModel.getInstance().get(new
		// ObjectId("549cc12ae160c2f93cb00170"));
		// System.out.println(res.getDescription());
		//
		// //----------------- Multi Get method -----------------
		// List<ObjectId> _objectIds = new ArrayList<ObjectId>();
		// _objectIds.add(new ObjectId("549cc12ae160c2f93cb00170"));
		// _objectIds.add(new ObjectId("549cc14de160590fe6ea6a05"));
		// List<Location> locations =
		// LocationModel.getInstance().multiGet(_objectIds);
		// for (Location l : locations){
		// System.out.println(l._id.toString());
		// }

		// Plan plan = new Plan();
		// Schedule schedule = new Schedule();
		// Map<String, Schedule> map = new HashMap<String, Schedule>();
		// map.put("54a4fb50e160e7d6cdc392e0", schedule);
		// plan.setSchedule(map);
		// PlanModel.getInstance().set(plan);
		
		Plan newPlan = PlanModel.getInstance().get(new ObjectId("54ba5debe16086ebc50275dd"));
		System.out.println(newPlan.getSchedule().get("54a4fb50e160e7d6cdc392e0").getVehicle());
	}
}
