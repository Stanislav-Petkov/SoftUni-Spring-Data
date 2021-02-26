package printAllMinionNamesTask7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class PrintAllMinionNames {

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

        printAllMinionNames();
    }

    private static void printAllMinionNames() throws SQLException {
        query = "select name from minions;";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> minionsNames = new ArrayList<>();
        while (resultSet.next()){
            minionsNames.add(resultSet.getString(1));
        }
        System.out.println(minionsNames);
        int j = minionsNames.size() - 1;
        for (int i = 0; i < minionsNames.size() / 2; i++,j--) {
            System.out.println(minionsNames.get(i));
            System.out.println(minionsNames.get(j));
        }
    }
}
