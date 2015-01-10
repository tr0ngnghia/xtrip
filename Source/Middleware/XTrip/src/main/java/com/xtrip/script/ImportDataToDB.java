package com.xtrip.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtrip.common.Common.LocationType;
import com.xtrip.model.LocationModel;
import com.xtrip.model.bean.Location;

public class ImportDataToDB extends BasicTask {
	private static final Logger logger = LoggerFactory
			.getLogger(ImportDataToDB.class);
	private Map<FileType, String> jsonFiles = new HashMap<FileType, String>();
	private List<String> excelFiles = new ArrayList<String>();
	private List<String> provinces = new ArrayList<String>();
	

	private enum FileType {
		LOCATIONS
	};

	private void init() {
		jsonFiles.put(FileType.LOCATIONS, "data/json/locations.json");

		provinces.add("BinhDinh");
		provinces.add("BinhThuan");
		provinces.add("DaNang");
		provinces.add("KhanhHoa");
		provinces.add("NinhThuan");
		provinces.add("PhuYen");
		provinces.add("QuangNam");
		provinces.add("QuangNgai");
		provinces.add("Hue");
		
		for(String province : provinces){
			excelFiles.add("data/excel/" + province + "/" + province + "-Restaurant-Cafe_Template.xls");
			excelFiles.add("data/excel/" + province + "/" + province + "-Shop_Template.xls");
			excelFiles.add("data/excel/" + province + "/" + province + "-Sport_Template.xls");
			excelFiles.add("data/excel/" + province + "/" + province + "-TourCompany_Template.xls");
			excelFiles.add("data/excel/" + province + "/" + province + "-Transport_Template.xls");
			excelFiles.add("data/excel/" + province + "/" + province + "-Travel_Template.xls");
			excelFiles.add("data/excel/" + province + "/" + province + "-Hotel_Template.xls");
		}
	}

	private String loadFile(String path) {
		StringBuilder sb = new StringBuilder();
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis, "UTF8"));
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private void importLocationDataFromJSON() {
		String data = loadFile(jsonFiles.get(FileType.LOCATIONS));
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
					location.setDescription(((String) lobj.get("Description"))
							.trim());
					location.setLongtitude((Double) lobj.get("Lng"));
					location.setLatitude((Double) lobj.get("Lat"));

					LocationModel.getInstance().set(location);
				}
			}
		} catch (ParseException ex) {
			logger.error(ex.toString());
		}
	}

	private void importLocationDataFromExcel() {
		try {
			for (String file : excelFiles) {
				File inputWorkbook = new File(file);
				if(!inputWorkbook.isFile()){
					continue;
				}		
			    Workbook wb = Workbook.getWorkbook(inputWorkbook);
			 
			    
			    Sheet sheet = wb.getSheet(0);
			    for (int i = 1; i < sheet.getRows(); i++) {			    
			    	Location location = new Location();
			    	for (int j = 0; j < sheet.getColumns(); j++) {
			          Cell cell = sheet.getCell(j, i);
							if (cell != null) {
								if (!cell.getContents().equalsIgnoreCase("")) {
									switch (j) {
									// Type
									case 0:
										String type = cell.getContents();
										if (type.equalsIgnoreCase("HOTEL")) {
											location.setType(new Double(Math.pow(2,1)).longValue());
										} else if (type.equalsIgnoreCase("RESTAURANT")) {
											location.setType(new Double(Math.pow(2,4)).longValue());
										} else if (type.equalsIgnoreCase("SHOP")) {
											location.setType(new Double(Math.pow(2,49)).longValue());
										} else if (type.equalsIgnoreCase("SUPERMARKET")) {
											location.setType(new Double(Math.pow(2,22)).longValue());
										} else if (type.equalsIgnoreCase("BANK")) {
											location.setType(new Double(Math.pow(2,12)).longValue());
										} else if (type.equalsIgnoreCase("TRAVEL_COMPANY")) {
											location.setType(new Double(Math.pow(2,50)).longValue());
										} else if (type.equalsIgnoreCase("TRANSPORT")) {
											location.setType(new Double(Math.pow(2,51)).longValue());
										} else if (type.equalsIgnoreCase("TRAVEL")) {
											location.setType(new Double(Math.pow(2,46)).longValue());
										}else if (type.equalsIgnoreCase("CAFE")) {
											location.setType(new Double(Math.pow(2,5)).longValue());
										}else if (type.equalsIgnoreCase("BAR")) {
											location.setType(new Double(Math.pow(2,20)).longValue());
										}else if (type.equalsIgnoreCase("KARAOKE")) {
											location.setType(new Double(Math.pow(2,29)).longValue());
										}else if (type.equalsIgnoreCase("MALL")) {
											location.setType(new Double(Math.pow(2,47)).longValue());
										}else if (type.equalsIgnoreCase("MARKET")) {
											location.setType(new Double(Math.pow(2,23)).longValue());
										}else if (type.equalsIgnoreCase("SPA")) {
											location.setType(new Double(Math.pow(2,31)).longValue());
										}else if (type.equalsIgnoreCase("TEAROOM")) {
											location.setType(new Double(Math.pow(2,38)).longValue());
										}else if (type.equalsIgnoreCase("ATM")) {
											location.setType(new Double(Math.pow(2,11)).longValue());
										}
										System.out.print(type);
										break;
									// Name
									case 1:
										String name = cell.getContents();
										location.setName(name);
										System.out.print(name);
										break;
									// Short Description
									case 2:
										break;
									// Long Description
									case 3:
										String longDescription = cell
												.getContents();
										location.setDescription(longDescription);
										System.out.print(longDescription);
										break;
									// Address
									case 4:
										String address = cell
												.getContents();
										location.setAddress(address);
										System.out.print(address);
										break;
									// Phone
									case 5:
										String phone = cell
												.getContents();
										location.setPhone(phone);
										System.out.print(phone);
										break;
									// Fax
									case 6:
										String fax = cell.getContents();
										location.setFax(fax);
										System.out.print(fax);
										break;
									// Email
									case 7:
										String email = cell
												.getContents();
										location.setEmail(email);
										System.out.print(email);
										break;
									// Web
									case 8:
										String website = cell
												.getContents();
										location.setWebsite(website);
										System.out.print(website);
										break;
									// Lat
									case 9:
										Double lat = Double.valueOf(cell
												.getContents());
										location.setLatitude(lat);
										System.out.print(lat);
										break;
									// Lng
									case 10:
										Double lng = Double.valueOf(cell
												.getContents());
										location.setLongtitude(lng);
										System.out.print(lng);
										// PostCode
									case 11:
										String postCode = cell.getContents();
										location.setPostCode(postCode);
										System.out.print(postCode);
										break;
									}
								}
							}
						}
			    		if(!location.getName().isEmpty()){
			    			boolean err = LocationModel.getInstance().set(location);
			    			System.out.println("");
			    			System.out.println("------------------------------------:" + err);
			    		}
					}
				}			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("done");
	}

	@Override
	public void run() {
		this.init();
		// this.importLocationDataFromJSON();
		this.importLocationDataFromExcel();
	}

	public static void main(String[] args) {
		ImportDataToDB instance = new ImportDataToDB();

		Date start = Calendar.getInstance().getTime();

		instance.ready();
		instance.run();
		instance.stop();

		Long interval = (Calendar.getInstance().getTimeInMillis() - start
				.getTime()) / 1000;
		Long hours = interval / 3600;
		Long minutes = interval / 60 - hours * 60;
		Long seconds = interval - hours * 3600 - minutes * 60;
		logger.info(String.format(
				"Running time: %d hour, %d minute, %d seconds", hours, minutes,
				seconds));
		logger.info("DONE");
	}
}
