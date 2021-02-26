package increaseMinionsAgeTask8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class IncreaseMinionsAge {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;
    private static BufferedReader reader;

    public static void main(String[] args) throws SQLException, IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        connection = DriverManager
                .getConnection(CONNECTION_STRING + DATABASE_NAME, properties);
        increaseMinionsAge();
    }

    private static void increaseMinionsAge() throws IOException, SQLException {
        List<String> list = Arrays.asList(reader.readLine().split("\\s+"));
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ids.add(Integer.parseInt(list.get(i)));
        }
        System.out.println();

        query = "UPDATE minions\n" +
                "SET name = LOWER(name), age = age + 1\n" +
                "WHERE id =  ?;";
        for (int i = 0; i < ids.size(); i++) {
            int currId = ids.get(i);
            statement = connection.prepareStatement(query);
            statement.setInt(1,currId);
            statement.executeUpdate();
        }

        query = "Select name,age\n" +
                "from minions;";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d\n",
                    resultSet.getString("name"),
                    resultSet.getInt("age"));
        }
    }
}
