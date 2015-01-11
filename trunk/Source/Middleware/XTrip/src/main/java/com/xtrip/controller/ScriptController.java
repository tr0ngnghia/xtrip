package com.xtrip.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.xtrip.common.Common.LocationType;
import com.xtrip.entity.XLocation;
import com.xtrip.model.LocationModel;
import com.xtrip.model.RegionModel;
import com.xtrip.model.bean.Location;
import com.xtrip.model.bean.Region;

@Controller
@RequestMapping("/script")
public class ScriptController {
	
	@RequestMapping("/import/json")
	public @ResponseBody
	XResponse importJson(@RequestParam(value = "apikey", required = false) String apikey){
		try{			
			XResponse res = new XResponse();
			List<Boolean> result = new ArrayList();
			
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
					JSONArray jsonArray = (JSONArray) jsonObject.get("data");
					for (Object obj : jsonArray) {
						ObjectMapper mapper = new ObjectMapper();
						XLocation xLocation = mapper.readValue(obj.toString(), XLocation.class);
						
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
						
						boolean err = LocationModel.getInstance().set(mLocation);
						result.add(err);
					}
				}
			} catch (ParseException ex) {
				System.out.println(ex.getMessage());
				return CommonResponse.SERVER_ERROR;
			}
			
			res.setData(result);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/updateAll")
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
						mLocation.setType(0L);
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
	
	@RequestMapping("/deleteAll")
	public @ResponseBody
	XResponse deleteAll(@RequestParam(value = "apikey", required = false) String apikey){
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
				for(ObjectId id : ids){
//					LocationModel.getInstance().remove(id);					
				}
			}		
			
			res.setData(result);
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
}
