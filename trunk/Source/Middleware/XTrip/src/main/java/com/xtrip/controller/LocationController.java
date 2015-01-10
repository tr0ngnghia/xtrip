package com.xtrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.common.CommonResponse;
import com.xtrip.common.XResponse;
import com.xtrip.entity.XLocation;
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
			if(location == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
			res.setData(toXLocation(location));
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
					   @RequestParam(value = "count", defaultValue = "10") int count,
					   @RequestParam(value = "type", required = false) Long type,
					   @RequestParam(value = "postCode", required = false) String postCode){
		try{
			XResponse res = new XResponse();
			
			//limit number of item each request
			if(count > 100){
				count = 100;
			}			
			
			List<ObjectId> ids = LocationModel.getInstance().getSliceLocationIds(type, postCode);
			if(ids != null){
				List<XLocation> xLocations = new ArrayList<XLocation>();
				int fromIndex = index < 0 ? 0 : (index > ids.size() ? ids.size() : index);
				int toIndex = fromIndex + count > ids.size() ? ids.size() : fromIndex + count;						
				List<Location> mLocations = LocationModel.getInstance().multiGet(ids.subList(fromIndex, toIndex));
				if(mLocations != null){
					for(Location mLocation : mLocations){
						xLocations.add(toXLocation(mLocation));
					}
				}
				res.setData(xLocations);
			}		
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/create")
	public @ResponseBody
	XResponse create(@RequestParam(value = "data", required = false) String data){
		try{
			XResponse res = new XResponse();
			
			if(data == null || data.isEmpty()){
				return CommonResponse.MISSING_PARAM;						
			}	
			
			ObjectMapper mapper = new ObjectMapper();
			XLocation xLocation = mapper.readValue(data, XLocation.class);
			
			Location mLocation = new Location();
			mLocation.setDateModified(System.currentTimeMillis());
			mLocation.setName(xLocation.getName());
			mLocation.setDescription(xLocation.getLongDesc());
			mLocation.setShortDesc(xLocation.getShortDesc());
			mLocation.setLatitude(xLocation.getLat());
			mLocation.setLongtitude(xLocation.getLng());
			mLocation.setImageUrls(xLocation.getGalary());
			mLocation.setIsPublic(xLocation.getIsPublic());
			mLocation.setIsShared(xLocation.getIsShared());
			mLocation.setType(xLocation.getType());
			mLocation.setAddress(xLocation.getAddress());
			mLocation.setPhone(xLocation.getPhone());
			mLocation.setFax(xLocation.getFax());
			mLocation.setPostCode(xLocation.getPostCode());
			mLocation.setEmail(xLocation.getEmail());
			mLocation.setWebsite(xLocation.getWebsite());
			mLocation.setPurchase(xLocation.getPurchase());
			mLocation.setUtils(xLocation.getUtils());
			mLocation.setCapacity(xLocation.getCapacity());
			mLocation.setStar(xLocation.getStar());
			mLocation.setRoom(xLocation.getRoom());
			
			LocationModel.getInstance().set(mLocation);
			
			res.setData(toXLocation(mLocation));
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody
	XResponse update(@RequestParam(value = "data", required = false) String data,	 		
	 		 @RequestParam(value = "id", required = false) String id){
		try{
			XResponse res = new XResponse();
			
			if(id == null || id.isEmpty() || data == null || data.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
			if(!ObjectId.isValid(id)){
				return CommonResponse.INVALID_PARAM;
			}
			
			Location mLocation = LocationModel.getInstance().get(new ObjectId(id));		
			if(mLocation == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			XLocation xLocation = mapper.readValue(data, XLocation.class);
			
			mLocation.setDateModified(System.currentTimeMillis());
			mLocation.setName(xLocation.getName());
			mLocation.setDescription(xLocation.getLongDesc());
			mLocation.setShortDesc(xLocation.getShortDesc());
			mLocation.setLatitude(xLocation.getLat());
			mLocation.setLongtitude(xLocation.getLng());
			mLocation.setImageUrls(xLocation.getGalary());
			mLocation.setIsPublic(xLocation.getIsPublic());
			mLocation.setIsShared(xLocation.getIsShared());
			mLocation.setType(xLocation.getType());
			mLocation.setAddress(xLocation.getAddress());
			mLocation.setPhone(xLocation.getPhone());
			mLocation.setAddress(xLocation.getAddress());
			mLocation.setPhone(xLocation.getPhone());
			mLocation.setFax(xLocation.getFax());
			mLocation.setPostCode(xLocation.getPostCode());
			mLocation.setEmail(xLocation.getEmail());
			mLocation.setWebsite(xLocation.getWebsite());
			mLocation.setPurchase(xLocation.getPurchase());
			mLocation.setUtils(xLocation.getUtils());
			mLocation.setCapacity(xLocation.getCapacity());
			mLocation.setStar(xLocation.getStar());
			mLocation.setRoom(xLocation.getRoom());
			
			LocationModel.getInstance().set(mLocation);
			res.setData(toXLocation(mLocation));
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody
	XResponse delete(@RequestParam(value = "id", required = false) String id){
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
	
	private XLocation toXLocation(Location mLocation){
		XLocation ret = new XLocation();
		
		if(mLocation != null){
			ret.setId(mLocation.getId());
			ret.setName(mLocation.getName());
			ret.setLongDesc(mLocation.getDescription());
			ret.setShortDesc(mLocation.getShortDesc());
			ret.setLat(mLocation.getLatitude());
			ret.setLng(mLocation.getLongtitude());
			ret.setType(mLocation.getType());
			ret.setDateCreated(mLocation.getDateCreated());
			ret.setDateModified(mLocation.getDateModified());
			ret.setIsPublic(mLocation.getIsPublic());
			ret.setIsShared(mLocation.getIsShared());
			ret.setGalary(mLocation.getImageUrls());
			ret.setAddress(mLocation.getAddress());
			ret.setPhone(mLocation.getPhone());
			ret.setFax(mLocation.getFax());
			ret.setPostCode(mLocation.getPostCode());
			ret.setEmail(mLocation.getEmail());
			ret.setWebsite(mLocation.getWebsite());
			ret.setPurchase(mLocation.getPurchase());
			ret.setUtils(mLocation.getUtils());
			ret.setCapacity(mLocation.getCapacity());
			ret.setStar(mLocation.getStar());
			ret.setRoom(mLocation.getRoom());
		}
		
		return ret;
	}
}
