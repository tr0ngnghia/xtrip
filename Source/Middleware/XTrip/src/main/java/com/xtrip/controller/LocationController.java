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
import com.xtrip.model.LocationModel;
import com.xtrip.model.bean.Location;


@Controller
@RequestMapping("/location")
public class LocationController{

	@RequestMapping("/get")
	public @ResponseBody
	 XResponse get(@RequestParam(value = "id", required = false) String id) {
		try{
			XResponse res = new XResponse();
			
			if(id == null || id.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
			if(!ObjectId.isValid(id)){
				return CommonResponse.INVALID_PARAM;
			}
			
			Location location = LocationModel.getInstance().get(new ObjectId(id));			
			res.setData(location);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;			
		}		
	}
	
	@RequestMapping("/getTotal")
	public @ResponseBody
	XResponse getTotal(){
		try{		
			XResponse res = new XResponse();
			int total = LocationModel.getInstance().getTotalNumberOfLocation();
			res.setData(total);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/getAll")
	public @ResponseBody
	XResponse getAll() {
		try{
			XResponse res = new XResponse();
			List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
			if(ids != null){
				List<Location> locations = LocationModel.getInstance().multiGet(ids);
				if(locations != null){
					res.setData(locations);
				}
			}		
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/getAllIds")
	public @ResponseBody
	XResponse getAllIds() {
		try{
			XResponse res = new XResponse();
			List<String> ids = new ArrayList<String>();
			List<ObjectId> objIds = LocationModel.getInstance().getAllLocationIds();
			for(ObjectId id : objIds){
				ids.add(id.toString());
			}
			res.setData(ids);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/getSlice")
	public @ResponseBody
	XResponse getSlice(@RequestParam(value = "index", defaultValue = "0") int index,
					   @RequestParam(value = "count", defaultValue = "10") int count){
		try{
			XResponse res = new XResponse();
			List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
			if(ids != null){
				int fromIndex = index < 0 ? 0 : (index > ids.size() ? ids.size() : index);
				int toIndex = fromIndex + count > ids.size() ? ids.size() : fromIndex + count;
						
				List<Location> locations = LocationModel.getInstance().multiGet(ids.subList(fromIndex, toIndex));
				if(locations != null){
					res.setData(locations);
				}
			}		
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/create")
	public @ResponseBody
	XResponse create(@RequestParam(value = "name", required = false) String name,
			 		 @RequestParam(value = "desc", required = false) String desc,
			 		 @RequestParam(value = "lat", defaultValue = "0") Double lat,
			 		 @RequestParam(value = "lng", defaultValue = "0") Double lng){
		try{
			XResponse res = new XResponse();
			
			if(name == null || desc == null){
				return CommonResponse.MISSING_PARAM;						
			}
			
			if(name.isEmpty() || desc.isEmpty()){
				return CommonResponse.INVALID_PARAM;
			}		
			
			Location location = new Location();
			location.setName(name);
			location.setDescription(desc);
			location.setDateCreated(System.currentTimeMillis());
			location.setDateModified(System.currentTimeMillis());
			location.setLatitude(lat);
			location.setLongtitude(lng);
			LocationModel.getInstance().set(location);
			
			res.setData(location);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody
	XResponse update(@RequestParam(value = "name", required = false) String name,
	 		 @RequestParam(value = "desc", required = false) String desc,
	 		 @RequestParam(value = "lat", required = false) Double lat,
	 		 @RequestParam(value = "lng", required = false) Double lng,
	 		 @RequestParam(value = "id", required = false) String id){
		try{
			XResponse res = new XResponse();
			
			if(id == null || id.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
			if(!ObjectId.isValid(id)){
				return CommonResponse.INVALID_PARAM;
			}
			
			Location location = LocationModel.getInstance().get(new ObjectId(id));		
			if(location == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
			
			if(name != null && name.isEmpty()){
				location.setName(name);
			}
			if(desc != null && desc.isEmpty()){
				location.setDescription(desc);
			}
			if(lat != null){
				location.setLatitude(lat);
			}
			if(lng != null){
				location.setLongtitude(lng);
			}			
			location.setDateModified(System.currentTimeMillis());
			LocationModel.getInstance().set(location);
			res.setData(location);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody
	XResponse create(@RequestParam(value = "id", required = false) String id){
		try{			
			XResponse res = new XResponse();
			if(id == null || id.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
			if(!ObjectId.isValid(id)){
				return CommonResponse.INVALID_PARAM;
			}
			LocationModel.getInstance().remove(new ObjectId(id));			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}

}
