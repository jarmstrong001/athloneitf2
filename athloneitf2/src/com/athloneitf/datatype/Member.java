package com.athloneitf.datatype;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import org.joda.time.*;

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
	@Column(name="Center")
	private Center center;
	
		
	
	public Center getCenter() {
		return center;
	}
	public void setCenter(Center center) {
		this.center = center;
	}
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
	
	public int getAgeAtTermStart(){
		LocalDate birthdate=new LocalDate(memberDob);
		LocalDate termStartDate=null;
		int currentYear=Calendar.getInstance().get(Calendar.YEAR);
		if(new LocalDate().isAfter(new LocalDate(currentYear,9,1))){
			termStartDate=new LocalDate(currentYear,9,1);
		}
		else termStartDate=new LocalDate(currentYear-1,9,1);
		Years age=Years.yearsBetween(birthdate,termStartDate);
		return age.getYears();
	}
}
