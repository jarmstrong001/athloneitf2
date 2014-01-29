package com.athloneitf.datatype;

import java.util.Date;

import javax.persistence.*;

@Entity
public class MemberScanIn {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MemberScanInId")
	private int id;
	@Column
	private Date scanInTime;
	@Column
	private int memberCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getScanInTime() {
		return scanInTime;
	}
	public void setScanInTime(Date scanInTime) {
		this.scanInTime = scanInTime;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	
	
}
