import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static final String GRINGOTTS_PU = "gringotts";
    public static final String SALES_PU = "sales";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(GRINGOTTS_PU);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);
        engine.run();
    }
}
