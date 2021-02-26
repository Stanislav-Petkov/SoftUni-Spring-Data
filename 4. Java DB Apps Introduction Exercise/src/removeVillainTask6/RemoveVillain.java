package removeVillainTask6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class RemoveVillain {

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
        removeVillain();
    }

    private static void removeVillain() throws SQLException, IOException {
        System.out.println("Enter villain id: ");
        int villainId = Integer.parseInt(reader.readLine());
        query = "SELECT * FROM villains WHERE id = ?;\n;";
        statement = connection.prepareStatement(query);
        statement.setInt(1,villainId);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()){
            System.out.println("No such villain was found");
        }else {
            String nameOfVillain = resultSet.getString("name");
            System.out.printf("%s was deleted\n", nameOfVillain );

            query = "select count(mv.minion_id)\n" +
                    "from villains AS v\n" +
                    "JOIN minions_villains AS mv\n" +
                    "ON v.id = mv.villain_id\n" +
                    "where mv.villain_id = '"+villainId+"';";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                deleteMinionsAndVillainIdsFromMinionsVillainsTable(resultSet,villainId);
                deleteVillainsFromVillainsTable(resultSet,villainId);
            }
        }
    }

    private static void deleteMinionsAndVillainIdsFromMinionsVillainsTable(ResultSet resultSet,int villainId) throws SQLException {
        System.out.printf("%d minions released",resultSet.getInt(1));

        query = "DELETE\n" +
                "FROM minions_villains\n" +
                "WHERE villain_id = '"+villainId+"';";
        statement = connection.prepareStatement(query);
        statement.executeUpdate();

    }
    private static void deleteVillainsFromVillainsTable(ResultSet resultSet,int villainId) throws SQLException {
        query = "DELETE\n" +
                "FROM villains\n" +
                "WHERE id = '" + villainId + "';";
        statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }
}