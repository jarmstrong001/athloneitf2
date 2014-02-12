package com.athloneitf.datatype;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Payment {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int paymentId;
	@Column
	private int memberCode;
	@Column
	private Date paymentDate;
	@Column
	private Date paymentTo;
	@Column
	private double paymentAmount;
	@Column
	private int paymentTypeId;
	
	public int getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getPaymentTo() {
		return paymentTo;
	}
	public void setPaymentTo(Date paymentTo) {
		this.paymentTo = paymentTo;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	
}
