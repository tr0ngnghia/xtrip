package com.xtrip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtrip.common.CommonResponse;
import com.xtrip.common.XResponse;
import com.xtrip.entity.XDay;
import com.xtrip.entity.XPlan;
import com.xtrip.model.LocationModel;
import com.xtrip.model.PlanModel;
import com.xtrip.model.bean.Plan;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	public static Map<String, XPlan> DUMP_DATA = new HashMap<String, XPlan>();
	
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
			
//			Plan plan = PlanModel.getInstance().get(new ObjectId(id));			
//			if(plan == null){
//				return CommonResponse.ITEM_NOTFOUND;
//			}					
//			res.setData(toXPlan(plan));
			
			XPlan xPlan = DUMP_DATA.get(id);
			if(xPlan == null){
				return CommonResponse.ITEM_NOTFOUND;
			}					
			res.setData(xPlan);
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
						
//			long total = PlanModel.getInstance().getTotalNumberOfPlan();
			long total = DUMP_DATA.size();
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
	
//			List<ObjectId> ids = PlanModel.getInstance().getAllPlanIds();
//			if(ids != null){
//				List<XPlan> xPlans = new ArrayList<XPlan>();
//				int fromIndex = index < 0 ? 0 : (index > ids.size() ? ids.size() : index);
//				int toIndex = fromIndex + count > ids.size() ? ids.size() : fromIndex + count;						
//				List<Plan> mPlans = PlanModel.getInstance().multiGet(ids.subList(fromIndex, toIndex));
//				if(mPlans != null){
//					for(Plan mPlan : mPlans){
//						xPlans.add(toXPlan(mPlan));
//					}
//				}
//				res.setData(xPlans);
//			}
			
			List<XPlan> xPlans = new ArrayList<XPlan>();
			int fromIndex = index < 0 ? 0 : (index > DUMP_DATA.size() ? DUMP_DATA.size() : index);
			int toIndex = fromIndex + count > DUMP_DATA.size() ? DUMP_DATA.size() : fromIndex + count;
			xPlans.addAll(DUMP_DATA.values());		
			res.setData(xPlans);
			
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
			
//			Plan mPlan = new Plan();			
//			mPlan.setName(xPlan.getName());
//			mPlan.setDescription(xPlan.getDesc());
//			mPlan.setStart(xPlan.getStart());
//		    mPlan.setEnd(xPlan.getEnd());
//		    mPlan.setNote(xPlan.getNote());
//		    mPlan.setOwnerId(xPlan.getOwner());
//			
//			PlanModel.getInstance().set(mPlan);
			
			String id = DUMP_DATA.size() + "";
			xPlan.setId(id);
			List<XDay> schedules = new ArrayList<XDay>();
			schedules.add(new XDay(1));
			schedules.add(new XDay(2));
			schedules.add(new XDay(3));
			
			xPlan.setSchedules(schedules);
			DUMP_DATA.put(id, xPlan);
			
			res.setData(xPlan);
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
			
			Plan mPlan = PlanModel.getInstance().get(new ObjectId(id));		
			if(mPlan == null){
				return CommonResponse.ITEM_NOTFOUND;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			XPlan xPlan = mapper.readValue(data, XPlan.class);
			
//			mPlan.setDateModified(System.currentTimeMillis());
//			mPlan.setName(xPlan.getName());
//			mPlan.setDescription(xPlan.getDesc());		
//		    mPlan.setStart(xPlan.getStart());
//		    mPlan.setEnd(xPlan.getEnd());
//		    mPlan.setNote(xPlan.getNote());
//		    mPlan.setOwnerId(xPlan.getOwner());
//		   		    
//			PlanModel.getInstance().set(mPlan);
			
			DUMP_DATA.put(DUMP_DATA.size()+"", xPlan);
			
			res.setData(xPlan);
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
//			PlanModel.getInstance().remove(new ObjectId(id));	
			
			DUMP_DATA.remove(id+"");
			
			return res;
		}catch(Exception ex){
			return CommonResponse.SERVER_ERROR;
		}
	}
	
	@RequestMapping("/getSlicePublic")
	public @ResponseBody
	XResponse getSlicePublic(@RequestParam(value = "index", defaultValue = "0") int index,
					   @RequestParam(value = "count", defaultValue = "10") int count){
		try{
			XResponse res = new XResponse();
			
			//limit number of item each request
			if(count > 100){
				count = 100;
			}	
	
//			List<ObjectId> ids = PlanModel.getInstance().getAllPlanIds();
//			if(ids != null){
//				List<XPlan> xPlans = new ArrayList<XPlan>();
//				int fromIndex = index < 0 ? 0 : (index > ids.size() ? ids.size() : index);
//				int toIndex = fromIndex + count > ids.size() ? ids.size() : fromIndex + count;						
//				List<Plan> mPlans = PlanModel.getInstance().multiGet(ids.subList(fromIndex, toIndex));
//				if(mPlans != null){
//					for(Plan mPlan : mPlans){
//						xPlans.add(toXPlan(mPlan));
//					}
//				}
//				res.setData(xPlans);
//			}		
			return res;
		}catch(Exception ex){
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
			ret.setNote(mPlan.getNote());
			ret.setOwner(mPlan.getOwnerId());
			ret.setBudget(mPlan.getTotalBuget());
			
			try{
			 String dayObj = "{      \"index\": 1,      \"locations\": [        {          \"order\": 0,          \"id\": \"549e95f8e4b03d6c04def7da\",          \"visited\": true,          \"vehicle\": \"CAR\"        },        {          \"order\": 2,          \"id\": \"549e95f8e4b03d6c04def7da\",          \"visited\": false,          \"vehicle\": \"FOOT\"        },        {          \"order\": 1,          \"id\": \"549e95f8e4b03d6c04def7da\",          \"visited\": false,          \"vehicle\": \"MOTOBIKE\"        }      ]    }";
			    ObjectMapper mapper2 = new ObjectMapper();
				XDay xXDay = mapper2.readValue(dayObj, XDay.class);
				List<XDay> schedules = new ArrayList<XDay>();
				schedules.add(xXDay);
				ret.setSchedules(schedules);
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			
		}
		
		return ret;
	}
}
