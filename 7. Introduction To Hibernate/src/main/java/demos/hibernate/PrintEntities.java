package demos.hibernate;

import demos.hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PrintEntities {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        try(SessionFactory sessionFactory = cfg.buildSessionFactory();
            Session session = sessionFactory.openSession() ){

            printAllStudents(session);

            printSeveralStudents(session);

            printStudentsWithStream(session);
        }
    }

    private static void printStudentsWithStream(Session session){
        session.beginTransaction();
        session.createQuery("FROM Student",Student.class)
                .setFirstResult(1)
                .setMaxResults(5)
                .getResultStream()
                .forEach(System.out::println);
        session.getTransaction().commit();
    }

    private static void printSeveralStudents(Session session) {
        session.beginTransaction();
        List<Student> threeStudents =
                session.createQuery("FROM Student", Student.class)
                        .setFirstResult(2)
                        .setMaxResults(3)
                        .list();
        session.getTransaction().commit();
        for(Student s : threeStudents){
            System.out.println(s);
        }
    }

    private static void printAllStudents(Session session) {
        session.beginTransaction();
        List<Student> students =
                session.createQuery("FROM Student",Student.class).list();
        //Print all students
        session.getTransaction().commit();
        for(Student s : students){
            System.out.println(s);
        }
    }
}
