package changeTownNamesCasingTask5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ChangeTownNamesCasing {

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

        changeAllTownNameToUpperCaseForACountry();
    }

    private static void changeAllTownNameToUpperCaseForACountry() throws IOException, SQLException {
        String country = reader.readLine();
        query = "UPDATE towns\n" +
                "SET `name` = UPPER(`name`)\n" +
                "WHERE country = ?;";
        statement = connection.prepareStatement(query);
        statement.setString(1,country);
        statement.executeUpdate();

        query = "SELECT COUNT(`name`)\n" +
                "FROM towns\n" +
                "WHERE country = ?;";
        statement = connection.prepareStatement(query);
        statement.setString(1,country);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            int numberOfTowns = resultSet.getInt(1);
            if (numberOfTowns == 0) {
                System.out.println("No town names were affected.");
                return;
            }else {
                System.out.printf("%d town names were affected.\n",
                        resultSet.getInt(1));
                printTownNames(country);
            }
        }
    }

    private static void printTownNames(String country) throws SQLException {
    query = "SELECT `name`\n" +
            "FROM towns\n" +
            "WHERE country = ?;";
        statement = connection.prepareStatement(query);
        statement.setString(1,country);
        ResultSet resultSet = statement.executeQuery();
        List<String> townNames = new ArrayList<>();
        while (resultSet.next()){
            townNames.add(resultSet.getString(1));
        }
        System.out.println(townNames);
    }

}