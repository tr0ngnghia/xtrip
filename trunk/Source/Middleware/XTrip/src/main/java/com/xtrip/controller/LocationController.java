package com.xtrip.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping("/getAll")
	public @ResponseBody
	XResponse getAll() {
		try{
			XResponse res = new XResponse();
			List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
			if(ids != null){
				List<XLocation> xLocations = new ArrayList<XLocation>();
				List<Location> mLocations = LocationModel.getInstance().multiGet(ids);
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
			
			Location mLocation = new Location();
			mLocation.setName(name);
			mLocation.setDescription(desc);
			mLocation.setDateCreated(System.currentTimeMillis());
			mLocation.setDateModified(System.currentTimeMillis());
			mLocation.setLatitude(lat);
			mLocation.setLongtitude(lng);
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
			mLocation.setDescription(xLocation.getShortDesc());
			mLocation.setLatitude(xLocation.getLat());
			mLocation.setLongtitude(xLocation.getLng());
			mLocation.setImageUrls(xLocation.getGalary());
			mLocation.setIsPublic(xLocation.getIsPublic());
			mLocation.setIsShared(xLocation.getIsShared());
			mLocation.setType(xLocation.getType());
			
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
	
	@RequestMapping("/importdb")
	public @ResponseBody
	XResponse importdb(@RequestParam(value = "apikey", required = false) String apikey){
		try{			
			XResponse res = new XResponse();
			
			if(apikey == null || apikey.isEmpty()){
				return CommonResponse.MISSING_APIKEY;
			}
			
			if(!apikey.equals("nghiapht")){
				return CommonResponse.WRONG_APIKEY;
			}
			
			StringBuilder sb = new StringBuilder();
			FileInputStream fis = null;
			BufferedReader br = null;
			try {
				fis = new FileInputStream("/home/admin/resync/locations.json");
				br = new BufferedReader(new InputStreamReader(fis, "UTF8"));
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					line = br.readLine();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return CommonResponse.SERVER_ERROR;
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					return CommonResponse.SERVER_ERROR;
				}
			}
			
			String data = sb.toString();
			try {
				if (data != null && !data.equals("")) {
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
					JSONArray jsonArray = (JSONArray) jsonObject.get("_locations");
					Iterator<JSONObject> iterator = jsonArray.iterator();
					while (iterator.hasNext()) {
						JSONObject lobj = (JSONObject) iterator.next();

						Location location = new Location();
						location.setName(((String) lobj.get("Name")).trim());
						location.setDescription(((String) lobj.get("Description")).trim());
						location.setLongtitude((Double) lobj.get("Lng"));
						location.setLatitude((Double) lobj.get("Lat"));
						
						LocationModel.getInstance().set(location);
					}
				}
			} catch (ParseException ex) {
				System.out.println(ex.getMessage());
				return CommonResponse.SERVER_ERROR;
			}
			
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
			ret.setShortDesc(mLocation.getDescription());
			ret.setLat(mLocation.getLatitude());
			ret.setLng(mLocation.getLongtitude());
			ret.setType(mLocation.getType());
			ret.setDateCreated(mLocation.getDateCreated());
			ret.setDateModified(mLocation.getDateModified());
			ret.setIsPublic(mLocation.getIsPublic());
			ret.setIsShared(mLocation.getIsShared());
			ret.setGalary(mLocation.getImageUrls());
		}
		
		return ret;
	}
}
