import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        String username = "root";
        String password = "root";

        Connector.createConnection(username, password, "orm_db");
        EntityManager<User> entityManager = new EntityManager<>(Connector.getConnection());

        User user = entityManager.findFirst(User.class, " id = 4");
        entityManager.delete(user);
        System.out.println();
//        User user = new User("Sasho","1234",21, new Date());
//        User user1 = new User("Stamat", "123", 25, new Date(),"5000");
//        entityManager.persist(user);
//        entityManager.persist(user1);
//        entityManager.doCreate(User.class);
//        User user = new User("Sasho", "12345", 21, new Date());
//        entityManager.persist(user);
//
//        List<User> users = (List<User>) entityManager.find(User.class," age > 23 ");
//        for(User userr : users){
//            System.out.println(userr.getUsername());
//        }
//
//        List<User> allusers = (List<User>) entityManager.find(User.class);
//        for(User userr: allusers){
//            System.out.println(userr.getUsername());
//        }

    }
}




























