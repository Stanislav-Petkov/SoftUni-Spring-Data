import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    private static final String GRINGOTTS_PU = "gringotts";
    private static final String SALES_PU = "sales";
    private static final String UNIVERSITY = "university";
    private static final String HOSPITAL = "hospital";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(HOSPITAL);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Engine engine = new Engine(entityManager);
//        engine.run();
    }
}
