package com.athloneitf.datatype;

import java.util.HashMap;
import java.util.Map;

public enum PaymentPeriod {
	DAY(1),WEEK(7),MONTH(30),YEAR(365),OTHER(999);
	
	private int periodNo;
	
	 private static Map<Integer, PaymentPeriod> map = new HashMap<Integer, PaymentPeriod>();
	
	static {
		for (PaymentPeriod period : PaymentPeriod.values()) {
			map.put(period.periodNo, period);
		}
	}
	
	private PaymentPeriod(final int length) {periodNo = length;}
	
	public static PaymentPeriod valueOf(int periodNo) {
		return map.get(periodNo);
	}
}
