package com.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class AddCoursesToAbirDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			int studentId1 = 1 ;
			Student student = session.get(Student.class, studentId1);
			System.out.println("The Student is " + student);
			System.out.println("Courses " + student.getCourses());
			
			// create more courses 
			Course course1 = new Course("Programing Java EE");
			Course course2 = new Course("Programing Go language");

			course1.addStudent(student);
			course2.addStudent(student);

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
