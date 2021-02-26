package demos.hibernate;

import demos.hibernate.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaDemoMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();
        User user = new User("John Smith");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.persist(user);
        User u = em.find(User.class, 1);
        System.out.printf("User: %s\n", u);

        //printUsersWithStream(em);
        printUsersWithCriteriaBuilder(em);
    }

    private static void printUsersWithCriteriaBuilder(EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<User> r = criteria.from(User.class);
        criteria.select(r).where(builder.like(r.get("name"),"J%"));
        em.createQuery(criteria)
                .setMaxResults(3).getResultStream().forEach(System.out::println);
    }

    private static void printUsersWithStream(EntityManager em) {
        em.getTransaction().begin();
        em.createQuery("SELECT u FROM User u", User.class)
                .setFirstResult(1)
                .setMaxResults(5)
                .getResultList()
                .stream()
                .forEach(System.out::println);
        em.getTransaction().commit();
    }
}



















