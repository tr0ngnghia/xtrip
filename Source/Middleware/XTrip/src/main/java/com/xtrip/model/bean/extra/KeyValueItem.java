/**
 * 
 */
package com.xtrip.model.bean.extra;

import java.util.Map;

/**
 * @author longnh
 */
public class KeyValueItem<K, V> extends SeriesValue{
	public K key;
	public V value;
	
	public KeyValueItem(Map<String, Object> props){		
		this.initialize(props);
	}


	/**
	 * 
	 */
	public KeyValueItem() {		
	}


	/**
	 * @param i
	 * @param d
	 */
	public KeyValueItem(K key, V value) {
		this.key = key;
		this.value = value;
	}


	@Override
	public void initialize(Map<String, Object> props) {
		if (props == null) return;    	
    	if (props.containsKey("key")) this.key = (K) props.get("key");    
    	if (props.containsKey("value")) this.value = (V) props.get("value"); 
	}


	@Override
	public KeyValueItem<K, V> newInstance(Map<String, Object> props) {
		return new KeyValueItem<K, V>(props);
	}
		
}
