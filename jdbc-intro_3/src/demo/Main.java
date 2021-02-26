package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter user:");
//        String user = scanner.nextLine().trim();
//        user = user.equals("") ? "root" : user;
//
//        System.out.println("Enter pass:");
//        String pass = scanner.nextLine().trim();
//        pass = pass.equals("") ? "root" : pass;

        Properties props = new Properties();

        String appConfigPath = Main.class.getClassLoader()
                .getResource("/db.properties").getPath();
        props.load(new FileInputStream(appConfigPath));

//        props.setProperty("user", user);
//        props.setProperty("password", pass);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("driver loaded successfully");

        try (Connection connection =
                     DriverManager
                             .getConnection("jdbc:mysql://localhost:3306/soft_unit?useSSL=false", props);
             PreparedStatement stmt =
                     connection.prepareStatement(
                             "SELECT * FROM employees WHERE salary > ?")) {

        System.out.println("Connected successfully");


            System.out.println("Enter minimal salary (default 20000): ");
//            String salaryStr = scanner.nextLine().trim();
            String salaryStr = props.getProperty("salary","20000");
//            double salary = salaryStr.equals("") ? 20000 : Double.parseDouble(salaryStr);

            double salary = Double.parseDouble(salaryStr);

            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("| %-15.15s | %-15.15s | %10.2f |\n",
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDouble("salary"));
            }
        }
    }
}



















