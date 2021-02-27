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

    // allowMultiQueries is a connection param that allowes to sent multiple querie to mysql
    private static final String URL = "jdbc:mysql://mysql:3306/?allowMultiQueries=true";

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private boolean isOpen;
    private boolean isDatabaseCreated;



    // constructor
    public DBHelper() {
        this.connection = null;
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
        this.isOpen = false;
        this.isDatabaseCreated = false;
    }


    // Get an instance of object singleton
    public static DBHelper getInstance() {

        if (instance == null) {
            instance = new DBHelper();
            instance.open();
            instance.createDatabase();
        }
        return instance;
    }

    // Open connection with database
    public void open() {
        if (this.isOpen)
            return;
        try {
            this.connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
            this.isOpen = true;
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
        System.out.println("\nQuery content: " + query + "\n");
        try {
            this.statement = this.connection.createStatement();
            this.statement.execute(query);
            

            System.out.println("Databse created successfully");
        } catch (SQLException e) {
            System.out.println("database error : " + e.getMessage());
        }
    }

}