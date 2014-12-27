package com.xtrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.model.LocationModel;
import com.xtrip.model.bean.Location;


@Controller
@RequestMapping("/location")
public class LocationController{

	@RequestMapping("/get")
	public @ResponseBody
	 Location get(@RequestParam(value = "id", defaultValue = "0") String id) {
		try{
			Location ret = LocationModel.getInstance().get(new ObjectId(id));
			if(ret != null){
				return ret;
			}
		}catch(Exception ex){
		}
		return null;
	}
	
	@RequestMapping("/getTotal")
	public @ResponseBody
	 int getTotal(@RequestParam(value = "id", defaultValue = "") String id){
		try{			
			int total = LocationModel.getInstance().getTotalNumberOfLocation();
			return total;
		}catch(Exception ex){
		}
		return 0;
	}
	
	@RequestMapping("/getAll")
	public @ResponseBody
	 List<Location> getAll() {
		List<Location> ret = new ArrayList<Location>();
		List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
		if(ids != null){
			List<Location> locations = LocationModel.getInstance().multiGet(ids);
			if(locations != null){
				ret.addAll(locations);
			}
		}		
		return ret;
	}
	
	@RequestMapping("/getAllIds")
	public @ResponseBody
	 List<String> getAllIds() {
		List<String> ret = new ArrayList<String>();
		List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
		for(ObjectId id : ids){
			ret.add(id.toString());
		}
		return ret;
	}
	
	@RequestMapping("/getSlice")
	public @ResponseBody
	 List<Location> getSlice(@RequestParam(value = "index", defaultValue = "0") int index,
	 		 				 @RequestParam(value = "count", defaultValue = "10") int count){
		List<Location> ret = new ArrayList<Location>();
		List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
		if(ids != null){
			int fromIndex = index < 0 ? 0 : (index > ids.size() ? ids.size() : index);
			int toIndex = fromIndex + count > ids.size() ? ids.size() : fromIndex + count;
					
			List<Location> locations = LocationModel.getInstance().multiGet(ids.subList(fromIndex, toIndex));
			if(locations != null){
				ret.addAll(locations);
			}
		}		
		return ret;
	}
	
	@RequestMapping("/create")
	public @ResponseBody
	 Location create(@RequestParam(value = "name", defaultValue = "new location") String name,
			 		 @RequestParam(value = "desc", defaultValue = "") String desc){
		try{
			Location location = new Location();
			location.setName(name);
			location.setDescription(desc);
			LocationModel.getInstance().set(location);
			
			return location;
		}catch(Exception ex){
		}
		return null;
	}
	
	@RequestMapping("/update")
	public @ResponseBody
	 Location update(@RequestParam(value = "name", defaultValue = "new location") String name,
			 		 @RequestParam(value = "desc", defaultValue = "") String desc,
			 		@RequestParam(value = "id", defaultValue = "") String id){
		try{
			Location location = LocationModel.getInstance().get(new ObjectId(id));
			location.setName(name);
			location.setDescription(desc);
			location.setDateModified(System.currentTimeMillis());
			LocationModel.getInstance().set(location);
			
			return location;
		}catch(Exception ex){
		}
		return null;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody
	 int create(@RequestParam(value = "id", defaultValue = "") String id){
		try{			
			LocationModel.getInstance().remove(new ObjectId(id));			
			return 0;
		}catch(Exception ex){
		}
		return -1;
	}

}
