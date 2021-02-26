package demos.hibernate;

import demos.hibernate.model.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PrintWithCriteriaBuilder {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        try(SessionFactory sessionFactory = cfg.buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery();
            // Set the class from which the objects are selected
            Root<Student> r = criteria.from(Student.class);

            criteria.select(r).where(builder.like(r.get("name"),"I%"));
            List<Student> studentList = session.createQuery(criteria).getResultList();
        }
    }
}

/*
session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery();
            Root<Student> r = criteria.from(Student.class);

            // Print all students starting with I
            criteria.select(r).where(builder.like(r.get("name"),"I%"));
            List<Student> studentList = session.createQuery(criteria).getResultList();
            for(Student s : studentList){
                System.out.println(s);
            }
            session.getTransaction().commit();
 */