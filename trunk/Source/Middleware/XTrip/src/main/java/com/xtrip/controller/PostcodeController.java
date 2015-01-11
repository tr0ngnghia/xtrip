package com.xtrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.common.CommonResponse;
import com.xtrip.common.XResponse;
import com.xtrip.entity.XRegion;
import com.xtrip.model.RegionModel;
import com.xtrip.model.bean.Region;

@Controller
@RequestMapping("/postcode")
public class PostcodeController {

	@RequestMapping("/getProvince")
	public @ResponseBody
	XResponse getProvince(){
		try{			
			XResponse res = new XResponse();			
			
			List<ObjectId> ids = RegionModel.getInstance().getIds((byte)0, "");
			if(ids != null){
				List<XRegion> xRegions = new ArrayList<XRegion>();
				List<Region> mRegions = RegionModel.getInstance().multiGet(ids);
				if(mRegions != null){
					for(Region mRegion : mRegions){
						xRegions.add(toXRegion(mRegion));
					}
				}
				res.setData(xRegions);
			}
			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/getDistrict")
	public @ResponseBody
	XResponse getDistrict(@RequestParam(value = "province", required = false) String province){
		try{			
			XResponse res = new XResponse();			
			
			List<ObjectId> ids = RegionModel.getInstance().getIds((byte)1, province);
			if(ids != null){
				List<XRegion> xRegions = new ArrayList<XRegion>();
				List<Region> mRegions = RegionModel.getInstance().multiGet(ids);
				if(mRegions != null){
					for(Region mRegion : mRegions){
						xRegions.add(toXRegion(mRegion));
					}
				}
				res.setData(xRegions);
			}
			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/getWard")
	public @ResponseBody
	XResponse getWard(@RequestParam(value = "district", required = false) String district){
		try{			
			XResponse res = new XResponse();			
			
			List<ObjectId> ids = RegionModel.getInstance().getIds((byte)2, district);
			if(ids != null){
				List<XRegion> xRegions = new ArrayList<XRegion>();
				List<Region> mRegions = RegionModel.getInstance().multiGet(ids);
				if(mRegions != null){
					for(Region mRegion : mRegions){
						xRegions.add(toXRegion(mRegion));
					}
				}
				res.setData(xRegions);
			}
			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	private XRegion toXRegion(Region mRegion){
		XRegion ret = new XRegion();		
		if(mRegion != null){
//			ret.setLevel(mRegion.getLevel());
			ret.setName(mRegion.getName());
//			ret.setParent(mRegion.getParent());
			ret.setPostcode(mRegion.getPostcode());
			ret.setType(mRegion.getType());
		}		
		return ret;
	}
	
}
