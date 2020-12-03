package com.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCourcesDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			// create an Instructor/InstructorDetail student object
           /* int theId = 10;
           
           Course course = session.get(Course.class, theId);
           System.out.println("the course " + course);
           
           session.delete(course); */
			
           System.out.println(session.get(Instructor.class, 1 ).getCourses());
           
           
           
           session.getTransaction().commit();


        }
		catch (Exception e) {
			e.printStackTrace();
		}finally {
        	
        	//clean up code
        	session.close();
			factory.close();
		}

	}

}
