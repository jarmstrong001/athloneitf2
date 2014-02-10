package com.athloneitf.main;

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
		
	
	}

    private static void queryMember(Session session) {
        Query query = session.createQuery("from Member");                 
        List <Member>list = query.list();
        java.util.Iterator<Member> iter = list.iterator();
        while (iter.hasNext()) {

        	Member person = iter.next();
            System.out.println(person.toString());

        }

        session.getTransaction().commit();

    }
    

    public static void createMember(Session session) {
    	Member person = new Member();
    	Member person2 = new Member();
    	Member person3 = new Member();
    	Member person4 = new Member();
    	

        person.setFirstName("Paul");
        person.setSurname("Fox");       
        person.setMemberCode(12345001);
        Calendar c=Calendar.getInstance();
        c.set(1980,2,12,0,0,0);
        person.setMemberDob(c.getTime());
        person.setInstructor(true);
        person.setScannedInStatus(false);
        session.save(person);
        
        person2.setFirstName("Kelly");
        person2.setSurname("McHugh");
        person2.setMemberCode(12346002);
        c.set(1999,3,22,0,0,0);
        person2.setMemberDob(c.getTime());
        person2.setInstructor(false);
        person2.setScannedInStatus(false);
        session.save(person2);
        
        person3.setFirstName("Joss");
        person3.setSurname("Armstrong");
        person3.setMemberCode(12346001);
        c.set(1978,1,16,0,0,0);
        person3.setMemberDob(c.getTime());
        person3.setInstructor(false);
        person3.setScannedInStatus(false);
        session.save(person3);
        
        person4.setFirstName("Isobel");
        person4.setSurname("Fox");
        person4.setMemberCode(12345002);
        c.set(1981,2,29,0,0,0);
        person4.setMemberDob(c.getTime());
        person4.setInstructor(false);
        person4.setScannedInStatus(false);
        session.save(person4);
        
    }
    
    

}
