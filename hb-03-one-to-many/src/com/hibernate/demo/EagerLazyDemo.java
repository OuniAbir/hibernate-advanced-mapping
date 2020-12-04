package com.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			int theId = 1;

			Instructor instructor = session.get(Instructor.class, theId);
			System.out.println("The instructor " + instructor);

			//System.out.println("The courses " + instructor.getCourses());
			
			//commi the tarnsaction 
			session.getTransaction().commit();
			//close the session            
			session.close();

			System.out.println("The courses " + instructor.getCourses());

		} finally {
			// clean up code
			factory.close();
		}

	}

}
