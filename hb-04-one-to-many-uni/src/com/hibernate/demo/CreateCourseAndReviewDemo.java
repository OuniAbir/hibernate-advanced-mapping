package com.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// create a course 
			Course course1 = new Course("Programing hibernate");
			Course course2 = new Course("Programing Spring");
			

			
			//add some reviews 
			course1.add(new Review("bad boock ever "));
			course1.add(new Review("WooooW boock ever "));

			// save the course 
			session.save(course1);
			session.save(course2);

			session.getTransaction().commit();

		} finally {

			// clean up code
			session.close();
			factory.close();
		}

	}

}
