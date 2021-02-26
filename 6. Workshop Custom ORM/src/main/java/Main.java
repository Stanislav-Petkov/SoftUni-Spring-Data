import annotations.Column;
import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchFieldException {

        // Create a connection to db
        String username = "root";
        String password = "root";

        Connector.createConnection(username,password,"orm_db");
        EntityManager<User> entityManager = new EntityManager<>(Connector.getConnection());

        //create a table users
         entityManager.doCreate(User.class);

        // delete a user from table users
        User user = entityManager.findFirst(User.class,"id = 3");
        entityManager.delete(user);
        System.out.println("User is deleted");

        // add user to table users
        User user1 = new User("Stamat","12345",21,new Date(),"5000");
        entityManager.persist(user1);
    }
}















