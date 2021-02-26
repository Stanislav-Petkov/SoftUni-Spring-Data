import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hib_code_first_ex_new");
//        EntityManager em = emf.createEntityManager();
//        Engine engine = new Engine(em);
//        engine.run();
    }
}
