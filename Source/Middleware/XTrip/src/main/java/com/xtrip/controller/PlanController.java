package com.xtrip.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.common.CommonResponse;
import com.xtrip.common.Enumaration;
import com.xtrip.common.XResponse;
import com.xtrip.entity.XDay;
import com.xtrip.entity.XLoc;
import com.xtrip.entity.XLocation;
import com.xtrip.entity.XPlan;
import com.xtrip.model.LocationModel;
import com.xtrip.model.PlanModel;
import com.xtrip.model.bean.Location;
import com.xtrip.model.bean.PLocation;
import com.xtrip.model.bean.Plan;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@RequestMapping("/get")
	public @ResponseBody
	 XResponse get(@RequestParam(value = "id", required = false) String id) {
		try{
			XResponse res = new XResponse();
			
			if(id == null || id.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
//			if(!ObjectId.isValid(id)){
//				return CommonResponse.INVALID_PARAM;
//			}
			
			Plan plan = PlanModel.getInstance().get(id);			
			if(plan == null){
				return CommonResponse.ITEM_NOTFOUND;
			}					
			res.setData(toXPlan(plan));
								
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
						
			long total = PlanModel.getInstance().getTotalNumberOfPlan();
			res.setData(total);
			return res;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return CommonResponse.SERVER_ERROR;
		}
	}		
	
	@RequestMapping("/getSlice")
	public @ResponseBody
	XResponse getSlice(@RequestParam(value = "index", defaultValue = "0") int index,
					   @RequestParam(value = "count", defaultValue = "10") int count){
		try{
			XResponse res = new XResponse();
			
			//limit number of item each request
			if(count > 100){
				count = 100;
			}	
	
			List<ObjectId> ids = PlanModel.getInstance().getAllPlanIds();
			if(ids != null){
				List<XPlan> xPlans = new ArrayList<XPlan>();
				int fromIndex = index < 0 ? 0 : (index > ids.size() ? ids.size() : index);
				int toIndex = fromIndex + count > ids.size() ? ids.size() : fromIndex + count;						
				List<Plan> mPlans = PlanModel.getInstance().multiGet(ids.subList(fromIndex, toIndex));
				if(mPlans != null){
					for(Plan mPlan : mPlans){
						xPlans.add(toXPlan(mPlan));
					}
				}
				res.setData(xPlans);
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
			XPlan xPlan = mapper.readValue(data, XPlan.class);
			
			if(xPlan == null || xPlan.getId() == null || xPlan.getId().isEmpty()){
				return CommonResponse.INVALID_PARAM;	
			}
			
			Plan mPlan = PlanModel.getInstance().get(xPlan.getId());		
			if(mPlan != null){
				return CommonResponse.DUPLICATE_KEY;
			}
				 
			mPlan = new Plan();			
			mPlan.setId(xPlan.getId());
			mPlan.setDateCreated(System.currentTimeMillis());
			mPlan.setDateModified(System.currentTimeMillis());
			mPlan.setName(xPlan.getName());
			mPlan.setDescription(xPlan.getDesc());		
		    mPlan.setStart(xPlan.getStart());
		    mPlan.setEnd(xPlan.getEnd());
		    mPlan.setNote(xPlan.getNote());
		    mPlan.setOwnerId(xPlan.getOwner());
		    mPlan.getBuget().setMoney(xPlan.getBudget());
		    mPlan.setDistance(xPlan.getDistance());
		    mPlan.setMember(xPlan.getMember());
		    mPlan.setProvince(xPlan.getProvince());
		    mPlan.setType(xPlan.getType());
		    
		    if(xPlan.getSchedulers() == null || xPlan.getSchedulers().isEmpty()){
		    	List<PLocation> schedules = new ArrayList<PLocation>();
		    	PLocation pLoc1 =  new PLocation((byte)1, "549e95f8e4b03d6c04def7e3");
		    	PLocation pLoc2 =  new PLocation((byte)1, "549e95f8e4b03d6c04def7e2");
		    	PLocation pLoc3 =  new PLocation((byte)2, "549e95f8e4b03d6c04def7df");
		    	PLocation pLoc4 =  new PLocation((byte)2, "549e95f8e4b03d6c04def7dc");
		    	schedules.add(pLoc1);
		    	schedules.add(pLoc2);
		    	schedules.add(pLoc3);
		    	schedules.add(pLoc4);
		    	mPlan.setSchedule(schedules);
		    }			
			PlanModel.getInstance().set(mPlan);
			
			res.setData(toXPlan(mPlan));
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
			
//			if(!ObjectId.isValid(id)){
//				return CommonResponse.INVALID_PARAM;
//			}
			
			Plan mPlan = PlanModel.getInstance().get(id);		
			if(mPlan == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
			
			XPlan xPlan = new XPlan();
			ObjectMapper mapper = new ObjectMapper();
			xPlan = mapper.readValue(data, XPlan.class);
			
			mPlan.setDateModified(System.currentTimeMillis());
			mPlan.setName(xPlan.getName());
			mPlan.setDescription(xPlan.getDesc());		
		    mPlan.setStart(xPlan.getStart());
		    mPlan.setEnd(xPlan.getEnd());
		    mPlan.setNote(xPlan.getNote());
		    mPlan.setOwnerId(xPlan.getOwner());
		    mPlan.getBuget().setMoney(xPlan.getBudget());
		    mPlan.setDistance(xPlan.getDistance());
		    mPlan.setMember(xPlan.getMember());
		    mPlan.setProvince(xPlan.getProvince());
		    mPlan.setType(xPlan.getType());
		    
		   		    
			PlanModel.getInstance().set(mPlan);			
	
			res.setData(toXPlan(mPlan));
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
			
//			if(!ObjectId.isValid(id)){
//				return CommonResponse.INVALID_PARAM;
//			}
			PlanModel.getInstance().remove(id);
				
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/schedule/update")
	public @ResponseBody
	XResponse updateSchedule(@RequestParam(value = "data", required = false) String data,	 		
	 		 @RequestParam(value = "id", required = false) String id){
		try{
			XResponse res = new XResponse();
			
			if(id == null || id.isEmpty() || data == null || data.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
//			if(!ObjectId.isValid(id)){
//				return CommonResponse.INVALID_PARAM;
//			}
			
			// check if plan is exist
			Plan mPlan = PlanModel.getInstance().get(id);		
			if(mPlan == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
			
			// parse json data
			JSONParser jsonParser = new JSONParser();
			JSONObject xDay = (JSONObject) jsonParser.parse(data);
			
			if(xDay == null || !xDay.containsKey("order") || !xDay.containsKey("plocs")){
				return CommonResponse.INVALID_PARAM;
			}
			
			byte day = Byte.parseByte(xDay.get("order").toString());
			JSONArray pLocs = (JSONArray)xDay.get("plocs");

			//remove old data
			List<PLocation> newSchedulers = new ArrayList<PLocation>();			
			if(mPlan.getSchedulers() != null){
				for(PLocation pLoc : mPlan.getSchedulers()){
					if(pLoc.getDay() != day){
						newSchedulers.add(pLoc);
					}
				}
			}
			//add new data
			if (pLocs != null) {
				for (int i = 0; i < pLocs.size(); i++) {
					ObjectMapper mapper = new ObjectMapper();
					XLoc xLoc = mapper.readValue(pLocs.get(i).toString(), XLoc.class);
					if (xLoc != null) {
						PLocation pLoc = new PLocation(day, xLoc.getId());
						pLoc.setOrder(xLoc.getOrder());
						newSchedulers.add(pLoc);
					}
				}
			}
			
			//put new schedule
		    mPlan.setSchedule(newSchedulers);
			PlanModel.getInstance().set(mPlan);			
	
			res.setData(toXPlan(mPlan));
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/schedule/delete")
	public @ResponseBody
	XResponse deleteSchedule(@RequestParam(value = "day", required = false) byte day,	 		
	 		 @RequestParam(value = "id", required = false) String id){
		try{
			XResponse res = new XResponse();
			
			if(id == null || id.isEmpty()){
				return CommonResponse.MISSING_PARAM;
			}			
			
			if(day <= 0){
				return CommonResponse.INVALID_PARAM;
			}
			
			// check if plan is exist
			Plan mPlan = PlanModel.getInstance().get(id);		
			if(mPlan == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
		
			//remove old data
			List<PLocation> newSchedulers = new ArrayList<PLocation>();			
			if(mPlan.getSchedulers() != null){
				for(PLocation pLoc : mPlan.getSchedulers()){
					if(pLoc.getDay() != day){
						newSchedulers.add(pLoc);
					}
				}
			}
			
			//put new schedule
		    mPlan.setSchedule(newSchedulers);
			PlanModel.getInstance().set(mPlan);			
	
			res.setData(toXPlan(mPlan));
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/schedule/suggest")
	public @ResponseBody 
	XResponse suggestPlan(@RequestParam(value = "day", required = false) byte day,
						@RequestParam(value = "budget", required = false) float budget,
						@RequestParam(value = "member", required = false) int member,
						@RequestParam(value = "postCode", required = false) String postcode,
						@RequestParam(value = "liveRate", required = false) int liveRate,
						@RequestParam(value = "eatRate", required = false) int eatRate,
						@RequestParam(value = "playRate", required = false) int playRate) {
		try {
			XResponse res = new XResponse();

			if (postcode == null) {
				return CommonResponse.MISSING_PARAM;
			}

			if (day <= 0 || budget <= 0 || member <= 0 || postcode.length() != 10 || liveRate <= 0 || eatRate <= 0 || playRate <= 0 || (liveRate + eatRate + playRate) != 100) {
				return CommonResponse.INVALID_PARAM;
			}

			// calculate budget for each person per day
			Plan suggestPlan = new Plan();
			int rEat = 3;
			int rLive = 1;
			int rPlay = 2;				
			float minEat = budget*eatRate/100/member/day/rEat;
			float minLive = budget*liveRate/100/member/day/rLive;
			float minPlay = budget*playRate/100/member/day/rPlay;
			
			// get suitable location from db
			List<ObjectId> locations = LocationModel.getInstance().getSliceLocationIds(null, postcode, null);
			if(locations == null || locations.isEmpty()){
				return CommonResponse.NO_SUITABLE_RESULT;
			}
			// init locations rerouece for each type
			List<Location> eatLocations = new ArrayList<Location>();
			List<Location> liveLocations = new ArrayList<Location>();
			List<Location> playLocations = new ArrayList<Location>();
			for(ObjectId locationId : locations){
				Location mLocation = LocationModel.getInstance().get(locationId);
				if(Enumaration.LIVE_LOCATION.contains(mLocation.getType())){
					if(liveLocations.size() < rLive*day && mLocation.getPrice() <= minLive){
						liveLocations.add(mLocation);
					}
				}					
				else if(Enumaration.EAT_LOCATION.contains(mLocation.getType())){
					if(eatLocations.size() < rEat*day && mLocation.getPrice() <= minEat){
						eatLocations.add(mLocation);
					}
				}
				else{
					if(playLocations.size() < rPlay*day && mLocation.getPrice() <= minPlay){
						playLocations.add(mLocation);
					}	
				}
			}
			
			// generate scheduler for plan
			List<PLocation> schedulers = new ArrayList<PLocation>();
			int liveIndex = 0;
			int eatIndex = 0;
			int playIndex = 0;
			for(byte d=1; d <= day; d++){
				byte order = 0;
				if(eatIndex < eatLocations.size()){
					PLocation pLoc = new PLocation(d, eatLocations.get(eatIndex++).getId());
					pLoc.setOrder(++order);
					schedulers.add(pLoc);
				}
				if(playIndex < playLocations.size()){
					PLocation pLoc = new PLocation(d, playLocations.get(playIndex++).getId());
					pLoc.setOrder(++order);
					schedulers.add(pLoc);
				}
				if(eatIndex < eatLocations.size()){
					PLocation pLoc = new PLocation(d, eatLocations.get(eatIndex++).getId());
					pLoc.setOrder(++order);
					schedulers.add(pLoc);
				}
				if(playIndex < playLocations.size()){
					PLocation pLoc = new PLocation(d, playLocations.get(playIndex++).getId());
					pLoc.setOrder(++order);
					schedulers.add(pLoc);
				}
				if(eatIndex < eatLocations.size()){
					PLocation pLoc = new PLocation(d, eatLocations.get(eatIndex++).getId());
					pLoc.setOrder(++order);
					schedulers.add(pLoc);
				}
				if(liveIndex < liveLocations.size()){
					PLocation pLoc = new PLocation(d, liveLocations.get(liveIndex++).getId());
					pLoc.setOrder(++order);
					schedulers.add(pLoc);
				}
			}

			suggestPlan.setSchedule(schedulers);
			res.setData(toXPlan(suggestPlan));
			return res;
		} catch (Exception ex) {
			return CommonResponse.SERVER_ERROR;
		}
	}
		
	private XPlan toXPlan(Plan mPlan){
		XPlan ret = new XPlan();
		
		if(mPlan != null){
			ret.setId(mPlan.getId());
			ret.setName(mPlan.getName());
			ret.setDesc(mPlan.getDescription());
			ret.setStart(mPlan.getStart());
			ret.setEnd(mPlan.getEnd());
			ret.setDateCreated(mPlan.getDateCreated());
			ret.setDateModified(mPlan.getDateModified());
			ret.setDistance((float)mPlan.getDistance());
			ret.setMembers(mPlan.getMember());
			ret.setProvince(mPlan.getProvince());
			ret.setType(mPlan.getType());
			ret.setNote(mPlan.getNote());
			ret.setOwner(mPlan.getOwnerId());
			ret.setBudget((float)mPlan.getBuget().getMoney());
			
			// convert list schedulers
			List<XDay> xScheduler = new ArrayList<XDay>();
			List<XLocation> xLocations = new ArrayList<XLocation>();
			if(mPlan.getSchedulers() != null){
				//init data for each day
				Map<Byte, List<XLoc>> xMap =  new HashMap<Byte, List<XLoc>>();
				List<ObjectId> locationIds = new ArrayList<ObjectId>();
				for(PLocation pLoc : mPlan.getSchedulers()){
					if(xMap.containsKey(pLoc.getDay())){
						List<XLoc> tmpList = new ArrayList<XLoc>(); 
						tmpList.addAll(xMap.get(pLoc.getDay()));
						tmpList.add(toXLoc(pLoc));
						xMap.put(pLoc.getDay(), tmpList);
					}
					else{
						xMap.put(pLoc.getDay(), Arrays.asList(new XLoc[]{toXLoc(pLoc)}));
					}
					locationIds.add(new ObjectId(pLoc.getLocationId()));
				}
				// put data to each day
				for(byte day : xMap.keySet()){
					XDay xDay = new XDay(day);				
					xDay.setPLocs(xMap.get(day));
					xScheduler.add(xDay);
				}
				//generate data for locations				
				List<Location> mLocations = LocationModel.getInstance().multiGet(locationIds);
				if(mLocations != null){
					for(Location mLocation : mLocations){
						xLocations.add(toXLocation(mLocation));
					}
				}
			}
			
			ret.setLocs(xLocations);
			ret.setSchedulers(xScheduler);
		}
		
		return ret;
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
	
	private XLoc toXLoc(PLocation pLoc){		
		XLoc ret =  new XLoc(pLoc.getOrder(), pLoc.getLocationId());		
		return ret;						
	}
}
