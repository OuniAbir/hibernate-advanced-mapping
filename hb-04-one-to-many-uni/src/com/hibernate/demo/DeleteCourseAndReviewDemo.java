package com.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

public class DeleteCourseAndReviewDemo {

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
			Course course = session.get(Course.class, 10);
			System.out.println("The course is " + course);
			System.out.println(course.getReviews());
			//Delete the course
			session.delete(course);
			
			session.getTransaction().commit();

		} finally {
			// clean up code
			session.close();
			factory.close();
		}

	}

}
