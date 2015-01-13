package com.xtrip.model.bean;


/**
 * @author longnh
 */
public class Schedule extends BaseBean {

	private long start;
	private long end;	

	public Schedule() {
		start = System.currentTimeMillis();
		end = System.currentTimeMillis();
	}

	public long getStart() {
		return this.start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return this.end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
}
