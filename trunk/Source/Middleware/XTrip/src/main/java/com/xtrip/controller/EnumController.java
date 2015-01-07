package com.xtrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.common.CommonResponse;
import com.xtrip.common.Enumaration;
import com.xtrip.common.XResponse;

@Controller
@RequestMapping("/enum")
public class EnumController {

	@RequestMapping("/location/type")
	public @ResponseBody
	XResponse getEnumLocationType(){
		try{			
			XResponse res = new XResponse();			
			res.setData(Enumaration.LOCATION_TYPE);			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/location/purchase")
	public @ResponseBody
	XResponse getEnumLocationPurchase(){
		try{			
			XResponse res = new XResponse();			
			res.setData(Enumaration.PURCHASE_TYPE);			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/location/utils")
	public @ResponseBody
	XResponse getEnumLocationUtils(){
		try{			
			XResponse res = new XResponse();			
			res.setData(Enumaration.UTILS_TYPE);			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
}
