package increaseAgeStoredProcedureTask9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;


public class IncreaseAge {

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

        //4.	Add Minion
        callIncreaseAgeStoredProcedure(2);
    }

    private static void callIncreaseAgeStoredProcedure(int minionId) throws SQLException {
        query = "{call usp_get_older(?)}";
        CallableStatement stmt = connection.prepareCall(query);
        stmt.setInt(1,minionId);
        stmt.execute();

        query = "SELECT name, age FROM minions WHERE id = '"+minionId+"' ";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.printf("%s %d",
                    resultSet.getString(1),
                    resultSet.getInt(2));
        }
    }
}