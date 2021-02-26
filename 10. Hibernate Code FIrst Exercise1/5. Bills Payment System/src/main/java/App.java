import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static final String BILLS_PAYMENT_SYSTEM_PU = "bills_payment_system";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(BILLS_PAYMENT_SYSTEM_PU);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        Engine engine = new Engine(entityManager);
//        engine.run();
    }
}
