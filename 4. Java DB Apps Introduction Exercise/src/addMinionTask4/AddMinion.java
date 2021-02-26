package addMinionTask4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;


public class AddMinion {

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
        addMinion();
    }

    /*
Minion: Robert 14 Berlin
Villain: Gru

Minion: Robert 14 Berlin
Villain: Carl

Minion: Carry 20 Eindhoven
Villain: Jimmy


     */
    private static void addMinion() throws IOException, SQLException {
        String[] minionDetails = reader.readLine().split(" ");
        String minionName = minionDetails[1];
        int minionAge = Integer.parseInt(minionDetails[2]);
        String minionTown = minionDetails[3];

        String[] villainDetails = reader.readLine().split(" ");
        String villainName = villainDetails[1];

        if(!checkIfTownExists(minionTown,"towns")){
            insertTownIntoTownsTableAndPrintMessage(minionTown,"towns");
        }
        if (!checkIfVillainExists(villainName, "villains")) {
            //add villain
            insertVillainToTableAndPrintMessage(villainName,"villains");
        }
        
        if(!checkIfMinionExists(minionName,"minions")){
            //add minion
            insertMinionToTableMinions(minionName,minionAge,"minions");
        }

        if(!checkIfTheVillainHasTheMinion(villainName,minionName)){
            connectVillainToMinion(villainName,minionName);
        }
    }

    private static void insertMinionToTableMinions(String minionName,int minionAge, String minions) throws SQLException {
        query = "INSERT INTO minions (`name`,age)\n" +
                "VALUES (?,?);";
        statement = connection.prepareStatement(query);
        statement.setString(1,minionName);
        statement.setInt(2,minionAge);
        statement.executeUpdate();
    }

    private static boolean checkIfMinionExists(String minionName, String minions) throws SQLException {
        query = "SELECT *\n" +
                "FROM minions \n" +
                "WHERE name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionName);
        ResultSet resultSet = statement.executeQuery();

        // If the id exists it will return more than 0
        return resultSet.next();
    }

    private static void connectVillainToMinion(String villainName, String minionName) throws SQLException {
        int villainId = getIdOfVillainFromVillains(villainName);
        int minionId = getIdOfMinionFromMinions(minionName);
        query = "INSERT INTO minions_villains(villain_id,minion_id)\n" +
                "VALUES ('"+villainId+"','"+minionId+"')";
        statement = connection.prepareStatement(query);
       // statement.setInt(1, villainId);
        //statement.setInt(2, minionId);
        statement.execute(query);
        //print that minion was added to villain
        System.out.printf("Successfully added %s to be minion of %s.",minionName,villainName);
    }

    private static boolean checkIfTheVillainHasTheMinion(String villainName, String minionName) throws SQLException {
        int villainId = getIdOfVillainFromVillains(villainName);
        int minionId = getIdOfMinionFromMinions(minionName);
        query = "SELECT * FROM minions_villains\n" +
                "WHERE villain_id = ? AND minion_id = ?;";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);
        statement.setInt(2, minionId);
        ResultSet resultSet = statement.executeQuery();
        //return id
        return resultSet.next();
    }

    private static int getIdOfVillainFromVillains(String villainName) throws SQLException {
        query = "SELECT id FROM villains where name = ?;\n";
        statement = connection.prepareStatement(query);
        statement.setString(1, villainName);
        ResultSet resultSet = statement.executeQuery();
        //return id
        int id = 0 ;
        while (resultSet.next()){
            id = resultSet.getInt(1);
        }
        return id;
    }

    private static int getIdOfMinionFromMinions(String minionName) throws SQLException {
        query = "SELECT id FROM minions where name = ?;\n";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionName);
        ResultSet resultSet = statement.executeQuery();
        int id = 0 ;
        while (resultSet.next()){
            id = resultSet.getInt(1);
        }
        return id;
    }

    private static boolean checkIfTownExists(String minionTown, String towns) throws SQLException {
        query = "SELECT *\n" +
                "FROM towns \n" +
                "WHERE name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionTown);
        ResultSet resultSet = statement.executeQuery();

        // If the id exists it will return more than 0
        return resultSet.next();
    }

    private static void insertTownIntoTownsTableAndPrintMessage(String minionTown, String towns) throws SQLException {
        query = "INSERT INTO towns (`name`)\n" +
                "VALUES (?);";
        statement = connection.prepareStatement(query);
        statement.setString(1,minionTown);
        statement.executeUpdate();

        System.out.printf("Town %s was added to the database.%n",minionTown);
    }

    private static void insertVillainToTableAndPrintMessage(String villainName, String villains) throws SQLException {
        query = "INSERT INTO villains (`name`,`evilness_factor`)\n" +
                "VALUES (?,'evil');";
        statement = connection.prepareStatement(query);
        statement.setString(1,villainName);
        statement.executeUpdate();

        System.out.printf("Villain %s was added to the database.\n",villainName);
    }

    private static boolean checkIfVillainExists(String entityName, String entityTable) throws SQLException {
        query = "SELECT *\n" +
                "FROM villains \n" +
                "WHERE name = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, entityName);
        ResultSet resultSet = statement.executeQuery();

        // If the id exists it will return more than 0
        return resultSet.next();
    }
}