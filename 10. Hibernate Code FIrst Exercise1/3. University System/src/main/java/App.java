import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static final String UNIVERSITY_SYSTEM_PU = "university_system";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(UNIVERSITY_SYSTEM_PU);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        Engine engine = new Engine(entityManager);
//        engine.run();
    }
}
