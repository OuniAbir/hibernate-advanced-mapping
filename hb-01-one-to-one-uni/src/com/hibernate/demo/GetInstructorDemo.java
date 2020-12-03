package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			int theId = 2;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			System.out.println("Found instructorDetail " + instructorDetail.toString());
			System.out.println("Found instructor" + instructorDetail.getInstructor());
			session.delete(instructorDetail);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			// handle connection leak
			session.getTransaction().commit();
			factory.close();
		}

	}

}
