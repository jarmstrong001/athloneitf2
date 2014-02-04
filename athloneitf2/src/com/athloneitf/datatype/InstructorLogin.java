package com.athloneitf.datatype;

import java.util.Date;

import javax.persistence.*;


@Entity
public class InstructorLogin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="InstructorLoginId")
	private int id;
	@Column(name="MemberBarCode")
	private int memberCode;
	@Column(name="LoginTime")
	private Date loginTime;
	@Column(name="LogoutTime")
	private Date logoutTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	
}
