package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			System.out.println("Found Instructor " + instructor.toString());

			if (instructor != null) {
				// also delete the instructorDetail
				session.delete(instructor);
			}
			session.getTransaction().commit();
			System.out.println("Done saving Objet ");

		} finally {
			factory.close();
		}

	}

}
