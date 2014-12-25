package com.xtrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtrip.model.bean.Plan;

@Controller
@RequestMapping("/api")
public class PlanJsonController{

	@RequestMapping("/plan")
	public @ResponseBody
	 Plan getShopInJSON(@RequestParam(value = "name", defaultValue = "nghiapht") String name, @RequestParam(value = "id", defaultValue = "100") int id) {

		Plan shop = new Plan();
		
		return shop;

	}

}
