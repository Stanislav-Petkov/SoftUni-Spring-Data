import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static final String HOSPITAL_PU = "hospital_db";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(HOSPITAL_PU);
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        Engine engine = new Engine(entityManager);
//        engine.run();
    }
}
