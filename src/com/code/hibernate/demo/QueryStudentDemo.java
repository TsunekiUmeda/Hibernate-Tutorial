package com.code.hibernate.demo;

import com.code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> theStudent = session.createQuery("from Student").getResultList();

            //display students
            displayStudents(theStudent);

            //query students: lastName='Doe'
            theStudent = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            //display students
            displayStudents(theStudent);

            //query students: lastName='Doe' of firstName = 'Daffy
            theStudent = session.createQuery(
                    "from Student s where s.lastName='Doe' or s.firstName='Daffy'").getResultList();

            //display students
            displayStudents(theStudent);

            //query student where email LIKE '%example.com
            theStudent = session.createQuery(
                    "from Student s where s.email like '%example.com'").getResultList();

            //display students
            displayStudents(theStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");

        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudent) {
        for (Student tempStudent : theStudent) {
            System.out.println(tempStudent);
        }
    }
}
