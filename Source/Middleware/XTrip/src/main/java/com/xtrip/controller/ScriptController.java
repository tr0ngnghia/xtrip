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
	
	@RequestMapping("/import/binhdinh")
	public @ResponseBody
	XResponse importBindinh(@RequestParam(value = "apikey", required = false) String apikey){
		try{			
			XResponse res = new XResponse();
			
			if(apikey == null || apikey.isEmpty()){
				return CommonResponse.MISSING_APIKEY;
			}
			
			if(!apikey.equals("nghiapht")){
				return CommonResponse.WRONG_APIKEY;
			}
			
			List<String> excelFiles = new ArrayList<String>();
			excelFiles.add("C:/excel/BinhDinh-Restaurant-Cafe_Template.xlsx");
			excelFiles.add("C:/excel/BinhDinh-Shop_Template.xlsx");
			excelFiles.add("C:/excel/BinhDinh-TourCompany_Template.xlsx");
			excelFiles.add("C:/excel/BinhDinh-Transport_Template.xlsx");
			excelFiles.add("C:/excel/BinhDinh-Travel_Template.xlsx");
			excelFiles.add("C:/excel/BinhDinh-Hotel_Template.xlsx");
			 
			int count = 0;
			
			try {
				for (String file : excelFiles) {
					InputStream is = new FileInputStream(file);
					XSSFWorkbook wb = new XSSFWorkbook(is);
					XSSFSheet sheet = wb.getSheetAt(0);
					XSSFRow row;
					XSSFCell cell;

					int rows = sheet.getPhysicalNumberOfRows();

					int cols = 0; // No of columns

					for (int r = 1; r < rows; r++) {
						row = sheet.getRow(r);
						if (row != null) {
							Location location = new Location();
							cols = sheet.getRow(r).getPhysicalNumberOfCells();
							for (int c = 0; c < cols; c++) {
								cell = row.getCell(c);
								if (cell != null) {
									if (!cell.getStringCellValue()
											.equalsIgnoreCase("")) {
										switch (c) {
										// Type
										case 0:
											String type = cell.getStringCellValue();
											if (type.equalsIgnoreCase("HOTEL")) {
												location.setType(LocationType.HOTEL);
											} else if (type
													.equalsIgnoreCase("RESTAURANT")) {
												location.setType(LocationType.RESTAURANT);
											} else if (type
													.equalsIgnoreCase("SHOP")) {
												location.setType(LocationType.SHOP);
											} else if (type
													.equalsIgnoreCase("SUPERMARKET")) {
												location.setType(LocationType.SUPERMARKET);
											} else if (type
													.equalsIgnoreCase("BANK")) {
												location.setType(LocationType.BANK);
											} else if (type
													.equalsIgnoreCase("TRAVEL_COMPANY")) {
												location.setType(LocationType.TRAVEL_COMPANY);
											} else if (type
													.equalsIgnoreCase("TRANSPORT")) {
												location.setType(LocationType.TRANSPORT);
											} else if (type
													.equalsIgnoreCase("TRAVEL")) {
												location.setType(LocationType.TRAVEL);
											}
											System.out.println(type);
											break;
										// Name
										case 1:
											String name = cell.getStringCellValue();
											location.setName(name);
											System.out.println(name);
											break;
										// Short Description
										case 2:
											break;
										// Long Description
										case 3:
											String longDescription = cell
													.getStringCellValue();
											location.setDescription(longDescription);
											System.out.println(longDescription);
											break;
										// Address
										case 4:
											String address = cell
													.getStringCellValue();
											location.setAddress(address);
											System.out.println(address);
											break;
										// Phone
										case 5:
											String phone = cell
													.getStringCellValue();
											location.setPhone(phone);
											System.out.println(phone);
											break;
										// Fax
										case 6:
											String fax = cell.getStringCellValue();
											location.setFax(fax);
											System.out.println(fax);
											break;
										// Email
										case 7:
											String email = cell
													.getStringCellValue();
											location.setEmail(email);
											System.out.println(email);
											break;
										// Web
										case 8:
											String website = cell
													.getStringCellValue();
											location.setWebsite(website);
											System.out.println(website);
											break;
										// Lat
										case 9:
											Double lat = Double.valueOf(cell
													.getStringCellValue());
											location.setLatitude(lat);
											System.out.println(lat);
											break;
										// Lng
										case 10:
											Double lng = Double.valueOf(cell
													.getStringCellValue());
											location.setLongtitude(lng);
											System.out.println(lng);
										// PostCode
										case 11:
											String postCode = cell.getStringCellValue();
											location.setPostCode(postCode);
											System.out.println(postCode);
											break;
										}
									}
								}
							}
							boolean err = LocationModel.getInstance().set(location);
							if(err){
								count++;
							}
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			res.setData(count);
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
			ret.setPurchase(mLocation.getPurchase());
			ret.setUtils(mLocation.getUtils());
			ret.setCapacity(mLocation.getCapacity());
			ret.setStar(mLocation.getStar());
			ret.setRoom(mLocation.getRoom());
		}
		
		return ret;
	}
}
