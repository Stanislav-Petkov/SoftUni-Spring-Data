//package course.springdata.codeFirstSingleTableStrategy;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.math.BigDecimal;
//
//public class JpaCodeFirstMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicles");
//        EntityManager em = emf.createEntityManager();
//        course.springdata.codeFirstSingleTableStrategy.Car car1 = new course.springdata.codeFirstSingleTableStrategy.Car("Audi A8", new BigDecimal(56000),"hybrid",5);
//        course.springdata.codeFirstSingleTableStrategy.Truck truck1 = new course.springdata.codeFirstSingleTableStrategy.Truck("Fuso Canter", new BigDecimal(120000),"gasoline",5.5);
//        course.springdata.codeFirstSingleTableStrategy.Plane plane1 = new course.springdata.codeFirstSingleTableStrategy.Plane("Boing", new BigDecimal(1200000),"gasoline",120);
//        em.getTransaction().begin();
//        em.persist(car1);
//        em.persist(truck1);
//        em.persist(plane1);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        course.springdata.codeFirstSingleTableStrategy.Car found = em.find(Car.class, 1L);
//        System.out.printf("Found car1: %s%n",found);
//
//        course.springdata.codeFirstSingleTableStrategy.Truck truck = em.find(Truck.class, 2L);
//        System.out.printf("Found truck1: %s%n", truck);
//
//        course.springdata.codeFirstSingleTableStrategy.Plane plane = em.find(Plane.class, 3L);
//        System.out.printf("Found plane1: %s%n",plane);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        em.createQuery("SELECT v FROM Vehicle v")
//                .getResultList()
//                .forEach(System.out::println);
//        em.getTransaction().commit();
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
