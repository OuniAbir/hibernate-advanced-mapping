package com.hibernate.demo;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			int theId = 4;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			System.out.println("Now let's delete instructorDetail " + instructorDetail.toString());
			 /* when we need to remove the CascadeType.REMOVE 
			  * delete the instructorDetail but not the instructor
			  * remove the associated object reference
			  * break bi-directional link then delete the instructorDetail
				instructorDetail.getInstructor().setInstructorDetail(null); */
			
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
