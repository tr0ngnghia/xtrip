package com.xtrip.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author longnh
 */
@SuppressWarnings("unused")
public class TextHelper {
	public static final String ESCAPE = "\\";	
	public static final ArrayList<String> MUST_ESCAPE = new ArrayList<String>(Arrays.asList("(", ")", "[", "]", "^", "*", ".", "\\", "?", "-"));
	public static final ArrayList<Character> NEW_LINES = new ArrayList<Character>(Arrays.asList('\n', '\r'));
	public static final String SINGLE_SPACE = " ";
	public static final Character SPACE_CHAR = ' ';
	public static final String DOUBLE_SPACE = "  ";
	
	public static String nomarlize(String value){
		String result = value;
		//REMOVE HEAD AND TAIL SPACE
		result = result.trim();
		//REPLACE ALL TAB TO SPACE
		result = result.replace("\t", SINGLE_SPACE);
		//REMOVE ALL DOUBLE SPACE
		while (result.contains(DOUBLE_SPACE))
			result = result.replace(DOUBLE_SPACE, SINGLE_SPACE);		
		
		return result;
	}
	
	public static String regex_escape(String value){
		return Pattern.quote(value);
	}
	
	public static String trimSymbols(String value){
		String newValue = value.trim();
		if ("".equals(newValue)) return newValue;		
		int i = 0, j = newValue.length() - 1;		
		while (isSymbol(newValue.charAt(i)) && i < j) i++;
		while (isSymbol(newValue.charAt(j)) && j > i) j--;
		if (i == j) j--;
		return newValue.substring(i, j + 1).trim();
	}
	
	public static Boolean isSymbol(Character c){
		if (Character.isLetterOrDigit(c)) return false;
		return true;
	}
	
	public static String convert2SingleLine(String value){
		if (value == null || "".equals(value.trim())) return "";
		String newValue = value;
		for(Character newline : NEW_LINES)			
			newValue = newValue.replace(newline, TextHelper.SPACE_CHAR);
		return newValue;	
	}
	
	public static String quote(String word){
		String newWord = word.replace("\"", "\\\"");
				
		newWord = "\"" + word + "\"";		
			
		return newWord;
	}
	
}
