/**
 * 
 */
package com.xtrip.model.bean.extra;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author longnh
 */
public class Series<T extends SeriesValue> {
	public ArrayList<T> items;

	public Series(Class<T> c, Map<String, Object> props) {
		if (props == null) {
			return;
		}
		if (props.containsKey("items")) {
			ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) props
					.get("items");
			this.items = new ArrayList<T>();
			for (int i = 0; i < list.size(); i++) {
				T value = (T) SeriesValueFactory.createSeriesValue(c,
						list.get(i));
				this.items.add(value);
			}
		}
	}

	/**
	 * 
	 */
	public Series() {
		this.items = new ArrayList<T>();
	}
}
