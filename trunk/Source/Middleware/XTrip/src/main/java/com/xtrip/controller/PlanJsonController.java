package com.xtrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.common.Tweet;

@Controller
@RequestMapping("/api")
public class PlanJsonController{

	@RequestMapping("/plan")
	public @ResponseBody
	Tweet getShopInJSON() {

		Tweet shop = new Tweet();
		shop.setText("nghiapht");

		return shop;

	}

}
