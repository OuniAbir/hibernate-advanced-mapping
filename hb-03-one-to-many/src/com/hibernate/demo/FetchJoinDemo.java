package com.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			int theId = 1;
			Query<Instructor> query = session.createQuery(	"select i from Instructor i " 
															+"JOIN FETCH i.courses "
															+ "where i.id=:theInstructorId", Instructor.class);

			// set parameter on query
			query.setParameter("theInstructorId", theId);
			// excute the query
			Instructor instructor = query.getSingleResult();
			System.out.println("The instructor " + instructor);

			// commi the tarnsaction
			session.getTransaction().commit();
			// close the session
			session.close();

			System.out.println("The courses " + instructor.getCourses());

		} finally {
			// clean up code
			factory.close();
		}

	}

}
