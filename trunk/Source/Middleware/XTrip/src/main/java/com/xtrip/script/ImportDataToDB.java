package com.xtrip.script;

import java.io.BufferedReader;
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

	private enum FileType {
		LOCATIONS
	};

	private void init() {
		jsonFiles.put(FileType.LOCATIONS, "data/json/locations.json");

		excelFiles.add("data/excel/BinhDinh-Restaurant-Cafe_Template.xlsx");
		excelFiles.add("data/excel/BinhDinh-Shop_Template.xlsx");
		excelFiles.add("data/excel/BinhDinh-TourCompany_Template.xlsx");
		excelFiles.add("data/excel/BinhDinh-Transport_Template.xlsx");
		excelFiles.add("data/excel/BinhDinh-Travel_Template.xlsx");
		excelFiles.add("data/excel/BinhDinh-Hotel_Template.xlsx");
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
						LocationModel.getInstance().set(location);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.init();
		this.importLocationDataFromJSON();
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
