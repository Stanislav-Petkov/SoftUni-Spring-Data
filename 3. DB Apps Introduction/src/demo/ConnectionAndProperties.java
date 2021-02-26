package demo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ConnectionAndProperties {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine().trim();
        user = user.equals("") ? "root" : user;

        System.out.print("Enter password default (root): ");
        String password = sc.nextLine().trim();
        password = password.equals("") ? "root" : password;

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        //1. Load jdbc driver (optional)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        //2. Connect to DB
        System.out.print("Driver loaded successfully");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://@localhost:3306/soft_uni?useSSL=false", props);
             PreparedStatement stmt =
                     connection.prepareStatement("SELECT * FROM employees WHERE salary > ?")) {

            System.out.println("Connected successfully");
            System.out.println("Enter minimal salary (default 20000): ");
            String salaryStr = sc.nextLine().trim();
            double salary = salaryStr.equals("") ? 20000 : Double.parseDouble(salaryStr);

            //salary parameter goes to the place of the first ? in the query, because the index is 1
            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            //use the position of the column
            while (rs.next()) {
                System.out.printf("| %-15.15s | %-15.15s | %10.2f |\n",
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(9)
                );
            }

            //connection and statement are auto-closed, they extend AutoCloseable
        }
    }
}

/*
package demo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine().trim();
        user = user.equals("") ? "root" : user;

        System.out.print("Enter password default (root): ");
        String password = sc.nextLine().trim();
        password = password.equals("") ? "root" : user;

        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        //1. Load jdbc driver (optional)
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
           e.printStackTrace();
           System.exit(0);
        }

        //2. Connect to DB
        System.out.print("Driver loaded successfully");
        Connection connection =
          //      DriverManager.getConnection(
        //             "jdbc:mysql://root:root@localhost:3306/soft_uni?useSSL=false");
               DriverManager.getConnection(
                        "jdbc:mysql://root:root@localhost:3306/soft_uni?useSSL=false",
                props);
        System.out.println("Connected successfully.");

        PreparedStatement stmt =
                connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        System.out.println("Enter minimal salary (default 2000): ");
        String salaryStr = sc.nextLine().trim();
        double salary = salaryStr.equals("") ? 20000 : Double.parseDouble(salaryStr);

        //salary parameter goes to the place of the first ? in the query, because the index is 1
        stmt.setDouble(1,salary);
        ResultSet rs = stmt.executeQuery();

        //use the column name
//        while(rs.next()){
//            System.out.printf("| %-15.15s | %-15.15s | %10.2f |\n",
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getDouble("salary")
//            );
//        }

        //use the position of the column
        while(rs.next()){
            System.out.printf("| %-15.15s | %-15.15s | %10.2f |\n",
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(9)
            );
        }
        connection.close();
    }
}






 */


















