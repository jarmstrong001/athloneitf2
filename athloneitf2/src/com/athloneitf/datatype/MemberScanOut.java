package com.athloneitf.datatype;

import java.util.Date;

import javax.persistence.*;

@Entity
public class MemberScanOut {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MemberScanOutId")
	private int id;
	@Column
	private Date scanOutTime;
	@Column
	private int memberCode;
	@Column 
	private ScanOutType scanOutType;
	
	public ScanOutType getScanOutType() {
		return scanOutType;
	}
	public void setScanOutType(ScanOutType type) {
		this.scanOutType = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getScanOutTime() {
		return scanOutTime;
	}
	public void setScanOutTime(Date scanOutTime) {
		this.scanOutTime = scanOutTime;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	
}
