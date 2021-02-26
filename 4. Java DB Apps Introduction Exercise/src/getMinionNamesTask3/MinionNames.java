package getMinionNamesTask3;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MinionNames {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        connection = DriverManager
                .getConnection(CONNECTION_STRING + DATABASE_NAME, properties);

        //3. Get Minions Names
        getMinionsNamesAndAge();
    }

    private static void getMinionsNamesAndAge() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        query = "SELECT v.name,m.name, m.age\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "JOIN minions AS m\n" +
                "ON mv.minion_id = m.id\n" +
                "WHERE v.id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);
        ResultSet resultSet = statement.executeQuery();
        int counter = 1;
        if (resultSet.next()) {
            System.out.printf("Villain: %s%n",
                    resultSet.getString("v.name"));
            while (resultSet.next()) {
                System.out.printf("%d. %s %d%n",
                        counter++,
                        resultSet.getString("m.name"),
                        resultSet.getInt("m.age"));
            }
        } else {
            System.out.printf("No villain with ID %d exists in the\n" +
                    "database.", villainId);
        }
    }
}
