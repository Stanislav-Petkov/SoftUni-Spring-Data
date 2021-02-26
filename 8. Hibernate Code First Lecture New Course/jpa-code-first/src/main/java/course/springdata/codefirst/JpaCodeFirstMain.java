package course.springdata.codefirst;

import course.springdata.codefirst.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JpaCodeFirstMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicles");
        EntityManager em = emf.createEntityManager();
        Car car1 = new Car("Audi A8", new BigDecimal(56000),"hybrid",5);
        Truck truck1 = new Truck("Fuso Canter", new BigDecimal(120000),"gasoline",5.5);
        em.getTransaction().begin();
        // Persist the car, set the plate to the car, persist the plate
        em.persist(car1);
        PlateNumber car1PlateNumber = new PlateNumber("CA1234",car1);
        car1.setPlate(car1PlateNumber);
        em.persist(car1PlateNumber);
        em.persist(truck1);
        em.getTransaction().commit();

        //Persist company with all its planes using CascadeType.ALL
        em.getTransaction().begin();
        Company company1 = new Company(new BigInteger("1234567890"), "Software AD");
        Plane plane1 = new Plane("Boing",new BigDecimal(1200000),"kerosine",120,company1);
        Plane plane2 = new Plane("AirBus",new BigDecimal(1500000),"kerosine",150,company1);
        Plane plane3 = new Plane("Pilatus Aircraft",new BigDecimal(890000),"kerosine",88,company1);
        em.persist(plane1);
        em.persist(plane2);
        em.persist(plane3);
        company1.getPlanes().add(plane1);
        company1.getPlanes().add(plane2);
        company1.getPlanes().add(plane3);
        em.persist(company1);
        em.getTransaction().commit();//end of transaction


        //Find different types of entities
        em.getTransaction().begin();
        Car found = em.find(Car.class, 1L);
        System.out.printf("Found car1: %s%n",found);

        Truck truck = em.find(Truck.class, 2L);
        System.out.printf("Found truck1: %s%n", truck);

        Plane plane = em.find(Plane.class, 3L);
        System.out.printf("Found plane1: %s%n",plane);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.createQuery("SELECT v FROM Vehicle v")
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
    }
}

























