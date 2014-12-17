/**
 * 
 */
package com.xtrip.model.bean.extra;

import java.util.Map;

/**
 * @author longnh
 */
public abstract class SeriesValue {
	 public abstract void initialize(Map<String, Object> props);
	 abstract SeriesValue newInstance(Map<String, Object> props);
}
