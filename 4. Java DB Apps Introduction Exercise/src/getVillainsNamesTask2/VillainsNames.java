package getVillainsNamesTask2;

import java.sql.*;
import java.util.Properties;

public class VillainsNames {

    private static final String CONNECTION_ADDRESS = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection(CONNECTION_ADDRESS + DATABASE_NAME , properties);

        String query =
                "SELECT v.name, COUNT(*) as `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY mv.villain_id\n" +
                "HAVING count > 15\n" +
                "ORDER BY count DESC";

        PreparedStatement preparedStatement = connection.prepareStatement(query) ;

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d%n",
                    resultSet.getString("name"),
                    resultSet.getInt("count"));
        }
    }
}

/*
package getVillainsNames;

import java.sql.*;
import java.util.Properties;

public class VillainsNames {

    private static final String CONNECTION_ADDRESS = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection(CONNECTION_ADDRESS + DATABASE_NAME , properties);

        String query =
                "SELECT v.name, COUNT(*) as `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY mv.villain_id\n" +
                "HAVING count > 15\n" +
                "ORDER BY count DESC";

        PreparedStatement preparedStatement = connection.prepareStatement(query) ;

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d%n",
                    resultSet.getString("name"),
                    resultSet.getInt("count"));
        }
    }
}


 */