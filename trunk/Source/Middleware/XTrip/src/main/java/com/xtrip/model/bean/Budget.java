package com.xtrip.model.bean;

import com.xtrip.common.Common.CurrenncyType;

/**
 * @author longnh
 */
public class Budget extends BaseBean {

	private double money;
	private CurrenncyType currencyType;

	public Budget() {
		money = 0.0;
		currencyType = CurrenncyType.VND;
	}

	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public CurrenncyType getCurrenncyType() {
		return this.currencyType;
	}

	public void setCurrenncyType(CurrenncyType currencyType) {
		this.currencyType = currencyType;
	}
}
