/**
 * 
 */
package com.xtrip.model.bean.extra;

import java.util.Map;

/**
 * @author longnh
 */
public class SeriesValueFactory {
	public static <T extends SeriesValue> SeriesValue createSeriesValue(Class<T> valueClass, Map<String, Object> props){
		try {
			return valueClass.newInstance().newInstance(props);
		} catch (Exception e) {
			return null;
		}
	}
}
