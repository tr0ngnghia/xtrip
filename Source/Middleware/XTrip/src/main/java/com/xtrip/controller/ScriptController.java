package com.xtrip.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
@RequestMapping("/script")
public class ScriptController {
	
	@RequestMapping("/import/json")
	public @ResponseBody
	XResponse importJson(@RequestParam(value = "apikey", required = false) String apikey){
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
	
	@RequestMapping("/update")
	public @ResponseBody
	XResponse update(@RequestParam(value = "apikey", required = false) String apikey){
		try{			
			XResponse res = new XResponse();
			
			if(apikey == null || apikey.isEmpty()){
				return CommonResponse.MISSING_APIKEY;
			}
			
			if(!apikey.equals("nghiapht")){
				return CommonResponse.WRONG_APIKEY;
			}
			
			List<ObjectId> ids = LocationModel.getInstance().getAllLocationIds();
			List<Boolean> result = new ArrayList<Boolean>();
			if(ids != null){				
				List<Location> mLocations = LocationModel.getInstance().multiGet(ids);
				if(mLocations != null){
					for(Location mLocation : mLocations){
						mLocation.setPostCode("6800000000");
						boolean err = LocationModel.getInstance().set(mLocation);
						result.add(err);
					}
				}
			}		
			
			res.setData(result);
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
			ret.setAddress(mLocation.getAddress());
			ret.setPhone(mLocation.getPhone());
			ret.setFax(mLocation.getFax());
			ret.setPostCode(mLocation.getPostCode());
			ret.setEmail(mLocation.getEmail());
			ret.setWebsite(mLocation.getWebsite());
		}
		
		return ret;
	}
}
