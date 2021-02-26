import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter database username default(root): ");
        String username = scanner.nextLine().trim();
        username = username.equals("") ? "root" : username;

        System.out.print("Enter database password default(root): ");
        String pass = scanner.nextLine().trim();
        pass = pass.equals("") ? "root" : pass;

        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", pass);

        // Load jdbc driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Connect to db diablo
        System.out.println("Driver loaded successfully");
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://@localhost:3306/diablo?useSSL=false", props);
        PreparedStatement stmt =
                connection.prepareStatement(
                        "SELECT u.first_name, u.last_name,COUNT(*) \n" +
                                "FROM users AS u\n" +
                                "JOIN users_games AS ug\n" +
                                "ON u.id = ug.user_id\n" +
                                "WHERE u.user_name = ?\n" +
                                "GROUP BY ug.user_id");

        System.out.println("Connected successfully");
        System.out.print("Enter user: ");
        String userFromTable = scanner.nextLine().trim();

        stmt.setString(1, userFromTable);
        ResultSet rs = stmt.executeQuery();


        // Use the position of the column
        if (rs.next()) {
            System.out.printf("User: %s\n", userFromTable);
            System.out.printf("%s %s has played %d games\n",
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3)
            );
        } else {
            System.out.println("No such user exists");
        }
    }
}

