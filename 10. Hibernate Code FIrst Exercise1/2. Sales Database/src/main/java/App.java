import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static final String SALES_PU = "sales";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(SALES_PU);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        Engine engine = new Engine(entityManager);
//        engine.run();
    }
}
