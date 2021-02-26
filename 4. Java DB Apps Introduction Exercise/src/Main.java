import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class Main {

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

        //2. Get Villains’ Names
        //getVillainsNamesAndCountOfMinions();
        //3. Get Minion Names
        //getMinionNameEx();
        //4. Add Minion
        //addMinionEx();
        //5. Change Town Names Casing
        //changeAllTownNameToUpperCaseForACountry();
        //6. *Remove Villain
        //removeVillain();
        //7. Print All Minion Names
        //printAllMinionNames()
        //8. Increase Minions Age
        //increaseMinionsAge()
        //9. Increase Age Stored Procedure
        //increaseAgeWithStoredProcedure();
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

    private static void increaseAgeWithStoredProcedure() throws IOException, SQLException {
        System.out.println("Enter minion id: ");
        int minionId = Integer.parseInt(reader.readLine());

        query = "CALL usp_get_older(?)";

        CallableStatement callableStatement = connection
                .prepareCall(query);
        callableStatement.setInt(1,minionId);
        callableStatement.execute();

        query = "SELECT name, age FROM minions WHERE id = '"+minionId+"' ";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.printf("%s %d",
                    resultSet.getString(1),
                    resultSet.getInt(2));
        }
    }

    private static void addMinionEx() throws IOException, SQLException {
        System.out.println("Enter minion parameters: ");
        String[] minionParameters = reader.readLine().split("\\s+");
        String minionName = minionParameters[0];
        int minionAge = Integer.parseInt(minionParameters[1]);
        String minionTown = minionParameters[2];

        System.out.println("Enter villain name: ");
        String villainName = reader.readLine();

        if(!checkIfEntityExistsByName(minionTown, "towns")){
            insertEntityInTown(minionTown);
        }
    }

    private static void insertEntityInTown(String minionTown) throws SQLException {
        query = "INSERT INTO towns (name , country) value(?, ?)";

        statement = connection.prepareStatement(query);
        statement.setString(1,minionTown);
        statement.setString(2,"NULL");

        statement.execute();
    }

    private static boolean checkIfEntityExistsByName(String entityName,String tableName) throws SQLException {
        query = "SELECT * FROM " + tableName + " WHERE name = ?";

        statement = connection.prepareStatement(query);
        statement.setString(1,entityName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getMinionNameEx() throws SQLException, IOException {
        System.out.println("Enter villain id: ");
        int villain_id = Integer.parseInt(reader.readLine());

        if(!checkIfEntityExists(villain_id,"villains")){
            System.out.printf("No villain with ID %d exists in the\n" +
                    "database.", villain_id);
            return;
        }

        System.out.printf("Villain: %s%n",getEntityNameById(villain_id,"villains"));
        getMinionsAndAgeByVillainId(villain_id);

    }

    private static void getMinionsAndAgeByVillainId(int villain_id) throws SQLException {
        query = "SELECT m.name, m.age\n" +
                "FROM minions AS m\n" +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?;";
        statement = connection.prepareStatement(query);
        statement.setInt(1,villain_id);

        ResultSet resultSet = statement.executeQuery();
        int minionNumber = 0;

        while (resultSet.next()){
            System.out.printf("%d. %s %d%n",
                    ++minionNumber,
                    resultSet.getString("name"),
                    resultSet.getInt(2));
        }
    }

    private static String getEntityNameById(int entityId,String tableName) throws SQLException {
        query = "SELECT name FROM " + tableName + " WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1,entityId);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;
    }

    private static boolean checkIfEntityExists(int villain_id, String villains) throws SQLException{
        query = "SELECT * FROM " + villains + " WHERE id = ?";

        statement = connection.prepareStatement(query);
        statement.setInt(1,villain_id);
        ResultSet resultSet = statement.executeQuery();

        // If the id exists it will return more than 0
        return resultSet.next();
    }

    private static void getVillainsNamesAndCountOfMinions() throws SQLException {
        query = "SELECT v.name, COUNT(mv.minion_id) as `count`\n" +
                "FROM villains AS v\n" +
                "JOIN minions_villains AS mv\n" +
                "ON v.id = mv.villain_id\n" +
                "GROUP BY v.name\n" +
                "HAVING `count` > 15\n" +
                "ORDER BY `count` DESC";

        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d",
                    resultSet.getString("name"),
                    resultSet.getInt("count"));
        }
    }
}

/*
public class Main {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db";

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection(CONNECTION_STRING + DATABASE_NAME, properties);

        String query = "SELECT name FROM minions";
        PreparedStatement statement = connection.
                prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();
        System.out.println();

        while(resultSet.next()){
            System.out.printf("%s %n", resultSet.getString(1));
        }
    }
}
 */
