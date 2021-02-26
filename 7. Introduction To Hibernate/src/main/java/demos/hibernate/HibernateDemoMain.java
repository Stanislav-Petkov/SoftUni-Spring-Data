package demos.hibernate;

import demos.hibernate.model.Student;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDemoMain {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        try(SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession()){

            Student student = new Student("Ivan Petrov3");
            try{
                session.beginTransaction();
                session.persist(student);
                session.getTransaction().commit();
            }catch(Exception e){
                if(session.getTransaction() != null){
                    session.getTransaction().rollback();
                }
                throw e;
            }

            session.beginTransaction();
            session.setHibernateFlushMode(FlushMode.MANUAL);
            session.get(Student.class,1);
            session.getTransaction().commit();
        }
    }
}

/*
public class HibernateDemoMain {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Student student = new Student("Ivan Petrov2");
            try {
                session.beginTransaction();
                //session.save(student);
                session.persist(student);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                throw e;
            }

            // Make another transaction in the same session
            session.beginTransaction();
            session.setHibernateFlushMode(FlushMode.MANUAL);
            Student s1 = session.get(Student.class,1);
            session.getTransaction().commit();

            System.out.printf("!!! Student: %s\n",s1);
        }
    }
 */






















