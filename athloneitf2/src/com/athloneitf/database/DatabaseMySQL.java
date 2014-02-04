package com.athloneitf.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.athloneitf.datatype.Member;
import com.athloneitf.datatype.InstructorLogin;
import com.athloneitf.main.SessionFactoryUtil;

public class DatabaseMySQL {

	public Session startSession(){
		Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}
	
	public Member loginInstructor(String instructorBarCode){
		Member returnValue=null;
		try {
			Session session=startSession();
			Query instructorLoginQuery = session.createQuery("FROM Member "+
			"WHERE Instructor=TRUE and MemberBarCode="+instructorBarCode);
			List<Member> instructor=instructorLoginQuery.list();
			System.out.println(instructor.size()+" records");
			session.getTransaction().commit();
			if (!(instructor.size()==0)){
				logoutAllInstructors();
				insertInstructorLogin(instructorBarCode);
				returnValue=instructor.get(0);
			}
		} catch(SQLException sqle){sqle.printStackTrace();}
		return returnValue;
	}
	
	public void insertInstructorLogin(String instructorBarCode){
		// Log instructor into database
		Session session=startSession();
		Query instructorLoginInsert = session.createSQLQuery("INSERT INTO "+
		"InstructorLogin(MemberBarCode,LoginTime) VALUES('"+instructorBarCode+"',now())");
		instructorLoginInsert.executeUpdate();
		session.getTransaction().commit();
		
	}
	
	private void logoutAllInstructors() throws SQLException{
		// Logout all instructors from database
		Session session=startSession();
		Query instructorLoginUpdate = session.createQuery("UPDATE InstructorLogin SET LogoutTime="+
						"now() WHERE LogoutTime IS NULL");
		instructorLoginUpdate.executeUpdate();
		session.getTransaction().commit();
	}
	
	
	

}
