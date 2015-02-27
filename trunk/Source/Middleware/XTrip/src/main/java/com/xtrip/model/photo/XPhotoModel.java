package com.xtrip.model.photo;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;


public class XPhotoModel {
	private static XPhotoModel instance;
	private Cloudinary cloudinary;
	private static final String CLOUD_NAME = "xtrip";
	private static final String API_KEY = "852384884337173";
	private static final String SECRET_KEY = "LG5GhGlB75PADzzvUb5h7IqK0W4";
	
	
	public XPhotoModel() {
		cloudinary = new Cloudinary(Cloudinary.asMap
				("cloud_name", CLOUD_NAME, 
				"api_key", API_KEY, 
				"api_secret",SECRET_KEY));
	}
	
	public static XPhotoModel getInstance() {
		return instance == null ? new XPhotoModel() : instance;
	}
	
	public String uploadPhoto(File toUpload){		
		try{
			Map uploadResult = cloudinary.uploader().upload(toUpload, Cloudinary.asMap());
			if(uploadResult != null){
				String publicId = (String) uploadResult.get("public_id");
				return publicId;
			}
		}
		catch(Exception ex){
			
		}
		return null;
	}
}
