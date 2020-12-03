package com.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteCoursesDemo {

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
			Course course = session.get(Course.class, 11);
			int theId = course.getInstructor().getId();
			Instructor instructor  = session.get(Instructor.class, theId);
			
			System.out.println("the instructor courses before deleting" + session.get(Instructor.class, 1).getCourses());
			session.delete(course); 

			session.getTransaction().commit();
			session.beginTransaction();
			System.out.println("the instructor courses after deleting" + session.get(Instructor.class, 1).getCourses());



        } finally {
        	
        	//clean up code
        	session.close();
			factory.close();
		}

	}

}
