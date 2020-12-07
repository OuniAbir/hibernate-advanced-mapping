package com.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

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

			// create a course 
			Course course1 = new Course("Programing hibernate");
			// save the course 
			session.save(course1);
			// create student 
			Student student1 = new Student("abir", "ouni", "abir@gmail.com") ;
			Student student2 = new Student("ddzv", "agbfi", "fqzef@gmail.com") ;
			course1.addStudent(student1);
			course1.addStudent(student2);
			// save a student 
			session.save(student1);
			session.save(student2);
			session.getTransaction().commit();
		} finally {

			// clean up code
			session.close();
			factory.close();
		}

	}

}
