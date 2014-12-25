package com.xtrip.controller;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.model.PlanModel;
import com.xtrip.model.bean.Plan;


@Controller
@RequestMapping("/api")
public class PlanJsonController{

	@RequestMapping("/plan")
	public @ResponseBody
	 Plan getShopInJSON(@RequestParam(value = "name", defaultValue = "nghiapht") String name, @RequestParam(value = "id", defaultValue = "100") int id) {
//		ObjectId _id = new ObjectId();
//		PlanModel.getInstance().get(_id);
		
		Plan plan = new Plan();
		
		return plan;

	}

}
