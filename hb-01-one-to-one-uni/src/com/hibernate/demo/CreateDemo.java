package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		// create a session
		Session session = factory.getCurrentSession();

		try {
			// create an Instructor/InstructorDetail student object
             System.out.println("creating a new instructor object ...");
            Instructor instructor = new Instructor("chad", "derby", "chad@gmail.com") ;
            InstructorDetail instructorDetail = new InstructorDetail("htttp://youtube//chad", "Luv to code") ;
            instructor.setInstructorDetail(instructorDetail); 

            Instructor instructor1 = new Instructor("abir", "ouni", "abir@gmail.com") ;
            InstructorDetail instructorDetail1 = new InstructorDetail("htttp://youtube/abir", "running ") ;
            instructor1.setInstructorDetail(instructorDetail1);
            
			session.beginTransaction();
			session.save(instructor);
			session.save(instructor1);

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done saving Objet ");

        } finally {
			factory.close();
		}

	}

}
