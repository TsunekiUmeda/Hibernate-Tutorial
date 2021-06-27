package com.code.hibernate.demo;

import com.code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            //create a student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "daffy@example.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            //findout the students's id: primary key
            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            //commit the transaction
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get compete: " + myStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");

        } finally {
            factory.close();
        }
    }
}
