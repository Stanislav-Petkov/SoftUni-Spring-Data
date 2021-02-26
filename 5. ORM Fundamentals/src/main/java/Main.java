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

        Connector.createConnection(username,password,"orm_db");
        EntityManager<User> entityManager = new EntityManager<>(Connector.getConnection());

        //User user = new User("Pesho","1234",25, new Date());
        //User user = new User(1,"Stamat","1234",25, new Date());
        //entityManager.persist(user);
       // User user = entityManager.findFirst(User.class, " age = 25");
        //User user = new User("Stamat2","12345",26,new Date());
        //entityManager.persist(user);

        User user = new User("ivan","12345",21,new Date());
        entityManager.persist(user);
        List<User> users = (List<User>) entityManager.find(User.class, " age > 23");
        for (User userr : users) {
            System.out.println(userr.getUsername());
        }
        System.out.println("");
        List<User> allUsers = (List<User>) entityManager.find(User.class);
        for (User userr : allUsers) {
            System.out.println(userr.getUsername());
        }
    }
}















