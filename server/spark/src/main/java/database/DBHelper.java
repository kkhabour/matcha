package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    public static DBHelper instance = null;

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String URL = "jdbc:mysql://mysql:3306/";

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private boolean isDb = false;

    public static DBHelper getInstance() {

        if (instance == null) {
            instance = new DBHelper();
            instance.open();
            instance.createDatabase();
            instance.isDb = true;
        }
        return instance;
    }

    public void open() {
        if (this.connection != null)
            return;
        try {
            this.connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet query(String query) {
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(query);

        } catch (SQLException e) {

            if (e.getErrorCode() == 1049) {
                String r = readfile("./src/main/java/database/setup.sql");
                this.query(r);
            }
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.resultSet;
    }

    public void printUser() {
        if (resultSet == null)
            return;
        try {
            while (this.resultSet.next()) {
                System.out.println(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private String readfile(String path) {
        String content = "";
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String r;
            while ((r = br.readLine()) != null)
                content += r;
            br.close();
        } catch (IOException e) {
            System.out.println("io exception: " + e.getMessage());
        }
        return content;
    }

    public void createDatabase() {
        String query = readfile("./src/main/java/database/setup.sql");
        System.out.println("Query content: " + query + "\n\n\n\n\n");
        try {
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate(query);

            System.out.println("Databse created successfully");
        } catch (SQLException e) {
            System.out.println("database error : " + e.getMessage());
        }
    }

}