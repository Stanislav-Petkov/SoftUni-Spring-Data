package initialSetupTask1;

import java.sql.*;
import java.util.Properties;

public class InitialSetup {

    private static final String CONNECTION_ADDRESS = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection(CONNECTION_ADDRESS + DATABASE_NAME , properties);

        String query = "SELECT name FROM minions";

        PreparedStatement preparedStatement = connection.prepareStatement(query) ;

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %n",resultSet.getString("name"));
        }
    }
}
