package com.athloneitf.main;

import java.text.ParseException;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.athloneitf.datatype.*;

public class InitializeData {

	public static void main(String[] args) {
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		createMember(session);
		
		queryMember(session);
		
		setupPaymentTypes(session);
		
		try{
		setupPayments(session);
		} catch(ParseException pe){ pe.printStackTrace();}
		
		session.getTransaction().commit();
	
	}

    private static void queryMember(Session session) {
        Query query = session.createQuery("from Member");                 
        List <Member>list = query.list();
        java.util.Iterator<Member> iter = list.iterator();
        while (iter.hasNext()) {

        	Member person = iter.next();
            System.out.println(person.toString());

        }

        

    }
    

    private static void createMember(Session session) {
    	Member person = new Member();
    	Member person2 = new Member();
    	Member person3 = new Member();
    	Member person4 = new Member();
    	

        person.setFirstName("Paul");
        person.setSurname("Fox");
        person.setCenter(Center.ATHLONE);
        person.setMemberCode(12345001);
        Calendar c=Calendar.getInstance();
        c.set(1980,2,12,0,0,0);
        person.setMemberDob(c.getTime());
        person.setInstructor(true);
        person.setScannedInStatus(false);
        session.save(person);
        
        person2.setFirstName("Kelly");
        person2.setSurname("McHugh");
        person2.setCenter(Center.ATHLONE);
        person2.setMemberCode(12346002);
        c.set(1999,3,22,0,0,0);
        person2.setMemberDob(c.getTime());
        person2.setInstructor(false);
        person2.setScannedInStatus(false);
        session.save(person2);
        
        person3.setFirstName("Joss");
        person3.setSurname("Armstrong");
        person3.setCenter(Center.ATHLONE);
        person3.setMemberCode(12346001);
        c.set(1978,1,16,0,0,0);
        person3.setMemberDob(c.getTime());
        person3.setInstructor(false);
        person3.setScannedInStatus(false);
        session.save(person3);
        
        person4.setFirstName("Isobel");
        person4.setSurname("Fox");
        person4.setCenter(Center.ATHLONE);
        person4.setMemberCode(12345002);
        c.set(1981,2,29,0,0,0);
        person4.setMemberDob(c.getTime());
        person4.setInstructor(false);
        person4.setScannedInStatus(false);
        session.save(person4);
        
    }
    
    private static void setupPaymentTypes(Session session){
    	PaymentType pt1=new PaymentType();
    	PaymentType pt2=new PaymentType();
    	PaymentType pt3=new PaymentType();
    	PaymentType pt4=new PaymentType();
    	PaymentType pt5=new PaymentType();
    	PaymentType pt6=new PaymentType();
    	PaymentType pt7=new PaymentType();
    	PaymentType pt8=new PaymentType();
    	PaymentType pt9=new PaymentType();
    	PaymentType pt10=new PaymentType();
    	PaymentType pt11=new PaymentType();
    	PaymentType pt12=new PaymentType();
    	PaymentType pt13=new PaymentType();
    	PaymentType pt14=new PaymentType();
    	PaymentType pt15=new PaymentType();
    	PaymentType pt16=new PaymentType();
    	
    	ClassType tkdCt=ClassType.TAEKWONDO;
    	ClassType skyCt=ClassType.SKYBOXING;
    	ClassType kickCt=ClassType.KICKBOXING;
    	
    	pt1.setPaymentTypeId(1);
    	pt1.setPaymentPeriod(PaymentPeriod.MONTH);
    	pt1.setPaymentAmount(30.00);
    	pt1.setPaymentTypeName("TKD Monthly");
    	pt1.setClassType(tkdCt);
    	session.save(pt1);
    	
    	pt2.setPaymentTypeId(2);
    	pt2.setPaymentPeriod(PaymentPeriod.MONTH);
    	pt2.setPaymentAmount(25.00);
    	pt2.setPaymentTypeName("TKD(Family) Monthly");
    	pt2.setClassType(tkdCt);
    	session.save(pt2);
    	
    	pt3.setPaymentTypeId(3);
    	pt3.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt3.setPaymentAmount(30.00);
    	pt3.setPaymentTypeName("IUTF Membership Adult");
    	pt3.setClassType(tkdCt);
    	session.save(pt3);
    	
    	pt4.setPaymentTypeId(4);
    	pt4.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt4.setPaymentAmount(15.00);
    	pt4.setPaymentTypeName("Insurance TKD");
    	pt4.setClassType(tkdCt);
    	session.save(pt4);
    	
    	pt13.setPaymentTypeId(5);
    	pt13.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt13.setPaymentAmount(25.00);
    	pt13.setPaymentTypeName("IUTF Membership Child");
    	pt13.setClassType(tkdCt);
    	session.save(pt13);
    	
    	pt14.setPaymentTypeId(6);
    	pt14.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt14.setPaymentAmount(360.00);
    	pt14.setPaymentTypeName("TKD Yearly");
    	pt14.setClassType(tkdCt);
    	session.save(pt14);
    	
    	pt15.setPaymentTypeId(7);
    	pt15.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt15.setPaymentAmount(300.00);
    	pt15.setPaymentTypeName("TKD(Family) Yearly");
    	pt15.setClassType(tkdCt);
    	session.save(pt15);
    	
    	pt5.setPaymentTypeId(10);
    	pt5.setPaymentPeriod(PaymentPeriod.DAY);
    	pt5.setPaymentAmount(5.00);
    	pt5.setPaymentTypeName("Skyboxing (TKD Member)");
    	pt5.setClassType(skyCt);
    	session.save(pt5);
    	
    	pt6.setPaymentTypeId(11);
    	pt6.setPaymentPeriod(PaymentPeriod.DAY);
    	pt6.setPaymentAmount(7.00);
    	pt6.setPaymentTypeName("Skyboxing (morning)");
    	pt6.setClassType(skyCt);
    	session.save(pt6);
    	
    	pt7.setPaymentTypeId(12);
    	pt7.setPaymentPeriod(PaymentPeriod.DAY);
    	pt7.setPaymentAmount(8.00);
    	pt7.setPaymentTypeName("Skyboxing (WKU Member)");
    	pt7.setClassType(skyCt);
    	session.save(pt7);
    	
    	pt8.setPaymentTypeId(13);
    	pt8.setPaymentPeriod(PaymentPeriod.DAY);
    	pt8.setPaymentAmount(10.00);
    	pt8.setPaymentTypeName("Skyboxing evening");
    	pt8.setClassType(skyCt);
    	session.save(pt8);
    	
    	pt9.setPaymentTypeId(20);
    	pt9.setPaymentPeriod(PaymentPeriod.MONTH);
    	pt9.setPaymentAmount(40.00);
    	pt9.setPaymentTypeName("Kickboxing");
    	pt9.setClassType(kickCt);
    	session.save(pt9);
    	
    	pt10.setPaymentTypeId(21);
    	pt10.setPaymentPeriod(PaymentPeriod.MONTH);
    	pt10.setPaymentAmount(35.00);
    	pt10.setPaymentTypeName("Kickboxing(Family)");
    	pt10.setClassType(kickCt);
    	session.save(pt10);
    	
    	pt11.setPaymentTypeId(22);
    	pt11.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt11.setPaymentAmount(30.00);
    	pt11.setPaymentTypeName("WKU Membership");
    	pt11.setClassType(kickCt);
    	session.save(pt11);
    	
    	pt12.setPaymentTypeId(24);
    	pt12.setPaymentPeriod(PaymentPeriod.YEAR);
    	pt12.setPaymentAmount(15.00);
    	pt12.setPaymentTypeName("Insurance Kickboxing");
    	pt12.setClassType(kickCt);
    	session.save(pt12);
    	    	
    	pt16.setPaymentTypeId(14);
    	pt16.setPaymentPeriod(PaymentPeriod.MONTH);
    	pt16.setPaymentAmount(50.00);
    	pt16.setPaymentTypeName("Skyboxing Month");
    	pt16.setClassType(skyCt);
    	session.save(pt16);
    }
    
    private static void setupPayments(Session session) throws ParseException{
    	// Paul insurance -> Aug 2014
    	Payment p1=new Payment();
    	p1.setMemberCode(12345001);
    	p1.setPaymentAmount(15.00);
    	p1.setPaymentDate(Common.dobDateFormat.parse("02/Sep/2013"));
    	p1.setPaymentTo(Common.dobDateFormat.parse("31/Aug/2014"));
    	p1.setPaymentTypeId(4);
    	session.save(p1);
    	
    	// Joss fees -> Aug 2014
    	Payment p2=new Payment();
    	p2.setMemberCode(12346001);
    	p2.setPaymentAmount(360.00);
    	p2.setPaymentDate(Common.dobDateFormat.parse("02/Sep/2013"));
    	p2.setPaymentTo(Common.dobDateFormat.parse("31/Aug/2014"));
    	p2.setPaymentTypeId(6);
    	session.save(p2);
    	
    	// Isobel fees -> Mar 2014    	
    	Payment p3=new Payment();
    	p3.setMemberCode(12345002);
    	p3.setPaymentAmount(30.00);
    	p3.setPaymentDate(Common.dobDateFormat.parse("02/Mar/2014"));
    	p3.setPaymentTo(Common.dobDateFormat.parse("31/Mar/2014"));
    	p3.setPaymentTypeId(1);
    	session.save(p3);
    	
    	// Paul fees -> Sep 2013
    	Payment p4=new Payment();
    	p4.setMemberCode(12345001);
    	p4.setPaymentAmount(30.00);
    	p4.setPaymentDate(Common.dobDateFormat.parse("02/Sep/2013"));
    	p4.setPaymentTo(Common.dobDateFormat.parse("30/Sep/2013"));
    	p4.setPaymentTypeId(1);
    	session.save(p4);
    	
    }
}
