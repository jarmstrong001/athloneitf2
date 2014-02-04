package com.athloneitf.main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.athloneitf.datatype.*;

public class Common {

	public static SimpleDateFormat dobDateFormat=new SimpleDateFormat("dd/MMM/yyyy");
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MMM/yyyy");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static void delay(int ms){
		try {
		    Thread.sleep(ms);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public static Session startSession(){
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
	
	public static Member getLoggedInInstructor(){
		Session session=startSession();
		Query instructorLoggedInQuery = session.createQuery("FROM InstructorLogin "+
				"WHERE LogoutTime=NULL");
				List<InstructorLogin> instructor=instructorLoggedInQuery.list();
				System.out.println(instructor.size()+" records");
				session.getTransaction().commit();
				return Common.getMember(""+instructor.get(0).getMemberCode());		
	}
	
	public static Member getMember(String barCode){
		Member returnMember=null;
		Session session=startSession();
		Query memberQuery = session.createQuery("FROM AITFMember "+
				"WHERE MemberBarCode="+barCode);
				List<Member> member=memberQuery.list();
				System.out.println(member.size()+" records");
				session.getTransaction().commit();
				if(member.size()>0) returnMember=member.get(0);
				return returnMember;		
	}
	
	public static void memberScanIn(Member member){
		Session session=startSession();
		MemberScanIn scanIn=new MemberScanIn();
		scanIn.setMemberCode(member.getMemberCode());
		scanIn.setScanInTime(new Date());
		member.setScannedInStatus(true);
		session.update(member);
		session.save(scanIn);
		session.getTransaction().commit();
		
	}
	
	public static void memberScanOut(Member member,boolean auto){
		Session session=startSession();
		MemberScanOut scanOut=new MemberScanOut();
		scanOut.setMemberCode(member.getMemberCode());
		scanOut.setScanOutTime(new Date());
		scanOut.setAutoScanOut(auto);
		member.setScannedInStatus(false);
		session.update(member);
		session.save(scanOut);
		session.getTransaction().commit();
	}
	
	public static void autoScanOut(){
		List<Member> memberList; 
		Session session=startSession();
		Query memberQuery = session.createQuery("FROM AITFMember ");
		memberList=memberQuery.list();
		session.getTransaction().commit();
		for(Member member:memberList){
			if(member.isScannedInStatus()) {
				autoScanOutMember(member);
			}
		}
		
	}
	
	public static void autoScanOutMember(Member member){
		Session session=startSession();
		List<MemberScanIn> latest=session.createQuery("FROM MemberScanIn ORDER BY scanInTime DESC").list();
		session.getTransaction().commit();
		if(latest.size()>0){
			Calendar c= Calendar.getInstance();
			System.out.println("Calendar time:"+
					c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
			c.add(Calendar.HOUR_OF_DAY,-3);
			System.out.println("Time to compare with scan in:"+
					c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
			System.out.println("Latest Scan in time:"+Common.dateFormat.format(latest.get(0).getScanInTime()));
			if(c.after(latest.get(0).getScanInTime())){
				memberScanOut(member,true);
				System.out.println("Auto scanning out "+member.getName());
			}
				
		}
		return;
	}
	
	public static boolean isMemberScannedIn(String barCode){
		autoScanOut();
		Member member=getMember(barCode);
		return member.isScannedInStatus();		
	}
	
	public static String getDayOfWeek(int i){
		switch(i){
	        case 1: return "Sunday"; 
	        case 2: return "Monday"; 
	        case 3: return "Tuesday"; 
	        case 4: return "Wednesday";
	        case 5: return "Thursday"; 
	        case 6: return "Friday"; 
	        case 7: return "Saturday"; 
	        default: return ("Invalid Day Number");
		}
	}


	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
}
