package com.xtrip.common;

public class CommonResponse {
		public static final XResponse SUCCESS = new XResponse(200, "Successful");
		
		//authentication error
		public static final XResponse MISSING_APIKEY = new XResponse(300, "Missing apikey");
		public static final XResponse WRONG_APIKEY = new XResponse(301, "Wrong apikey");
		
		//client error
		public static final XResponse MISSING_PARAM = new XResponse(400, "Missing parameter");
		public static final XResponse INVALID_PARAM = new XResponse(401, "Invalid parameter");
		
		//server error
		public static final XResponse SERVER_ERROR = new XResponse(500, "Server error");
		public static final XResponse ITEM_NOTFOUND = new XResponse(501, "Item is not found");
		public static final XResponse DUPLICATE_KEY = new XResponse(502, "Item id is already exist");
}
