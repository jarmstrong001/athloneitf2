package com.athloneitf.datatype;

import java.util.Date;

import javax.persistence.*;

import com.athloneitf.main.Common;

@Entity
public class Member {
	@Column(name="MemberFirstName")
	private String firstName;
	@Column(name="MemberSurname")
	private String surname;
	@Column(name="DateOfBirth")
	private Date memberDob;
	@Id
	@Column(name="MemberBarCode")
	private int memberCode;
	@Column(name="Instructor")
	private boolean instructor;
	@Column(name="ScannedInStatus")
	private boolean scannedInStatus;
	
		
	
	public boolean isScannedInStatus() {
		return scannedInStatus;
	}
	public void setScannedInStatus(boolean scannedInStatus) {
		this.scannedInStatus = scannedInStatus;
	}
	public boolean isInstructor() {
		return instructor;
	}
	public void setInstructor(boolean instructor) {
		this.instructor = instructor;
	}
	public Date getMemberDob() {
		return memberDob;
	}
	public void setMemberDob(Date memberDob) {
		this.memberDob = memberDob;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String memberFirstName) {
		firstName = memberFirstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String memberSurname) {
		surname = memberSurname;
	}
	
	public String getName() {
		return firstName+" "+surname;
	}
	
	public String toString(){
		return getName()+(isInstructor() ? "\t (Instructor)":"");
	}
	
	public String getString(){
		return getFirstName()+" "+getSurname()+"\t"+
	Common.dobDateFormat.format(getMemberDob())+"\t"+getMemberCode()+
	(isInstructor() ? "\tinstructor":"");
	}
}
