package com.xtrip.script;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtrip.model.LocationModel;
import com.xtrip.model.bean.Location;

public class ImportDataToDB extends BasicTask {
	private static final Logger logger = LoggerFactory
			.getLogger(ImportDataToDB.class);
	private Map<FileType, String> files = new HashMap<FileType, String>();

	private enum FileType {
		LOCATIONS
	};

	private void init() {
		files.put(FileType.LOCATIONS, "data/locations.json");
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
	private void importLocationData() {
		String data = loadFile(files.get(FileType.LOCATIONS));
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
			logger.error(ex.toString());
		}
	}

	@Override
	public void run() {
		this.init();
		this.importLocationData();
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
