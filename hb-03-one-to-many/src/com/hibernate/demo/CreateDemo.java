package com.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			// create an Instructor/InstructorDetail student object
            System.out.println("creating a new instructor object ...");
            Instructor instructor = new Instructor("abir", "ouni", "chad@gmail.com") ;
            InstructorDetail instructorDetail = new InstructorDetail("http://youtube/abir", "luv2code") ;
            instructor.setInstructorDetail(instructorDetail);
            
            
            Course course1 = new Course("Programing hibernate");
            Course course2 = new Course("Programing Spring");
            //add courses to instructor
            instructor.add(course1);
            instructor.add(course2);

            
			session.beginTransaction();
			
			// save everything
			session.save(instructor);
			session.save(instructorDetail);
			
			session.save(course1);
			session.save(course2); 
			
			session.getTransaction().commit();

        } finally {
        	
        	//clean up code
        	session.close();
			factory.close();
		}

	}

}
