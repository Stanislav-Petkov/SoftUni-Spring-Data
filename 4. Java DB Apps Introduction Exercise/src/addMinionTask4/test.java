
package addMinionTask4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;


public class test {

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
    private static void addMinion() throws IOException, SQLException {
        String[] minionDetails = reader.readLine().split(" ");
        String minionName = minionDetails[1];
        int minionAge = Integer.parseInt(minionDetails[2]);
        String minionTown = minionDetails[3];

        String[] villainDetails = reader.readLine().split(" ");
        String villainName = villainDetails[1];

       // int id = getIdOfVillainFromVillains(villainName);
        connectVillainToMinion(villainName,minionName);
    }

    private static void connectVillainToMinion(String villainName, String minionName) throws SQLException {
        int villainId = 17;
        int minionId = 55;
        query = "INSERT INTO minions_villains(minion_id,villain_id)\n" +
                "VALUES ('"+villainId+"', '"+minionId+"')";
        statement = connection.prepareStatement(query);
        //statement.setInt(1, minionId);
        //statement.setInt(2, villainId);
        statement.executeUpdate(query);
        //print that minion was added to villain
        System.out.printf("Successfully added %s to be minion of %s.",minionName,villainName);
    }
}
    /*

Minion: Carry 20 Eindhoven
Villain: Jimmy


Minion: Robert 14 Berlin
Villain: Gru

Minion: Robert 14 Berlin
Villain: Carl

Minion: Carry 20 Eindhoven
Villain: Jimmy


     */
