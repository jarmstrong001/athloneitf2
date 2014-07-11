package com.athloneitf.datatype;

import javax.persistence.*;

@Entity
public class PaymentType {

	@Id
	@Column(name="PaymentTypeId")
	int paymentTypeId;
	@Column
	String paymentTypeName;
	@Column
	double paymentAmount;
	@Column
	int paymentPeriod;
	@Column
	ClassType classType;
	
	public PaymentPeriod getPaymentPeriod() {
		return PaymentPeriod.valueOf(paymentPeriod);
	}
	public void setPaymentPeriod(PaymentPeriod pp) {
		paymentPeriod = pp.ordinal();
	}
	
	public int getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double d) {
		this.paymentAmount = d;
	}
	
	public ClassType getClassType(){
		return classType;
	}
	
	public void setClassType(ClassType ct){
		this.classType=ct;
	}
	
	public String toString(){
		return String.format("€%9.2f  %s",paymentAmount,paymentTypeName);
	}
}
