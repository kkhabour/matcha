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
    private static final String URL = "jdbc:mysql://192.168.99.100:3306/matcha";

    private Connection          connection = null;
    private Statement           statement = null;
    private PreparedStatement   preparedStatement = null;
    private ResultSet           resultSet = null;


    public static DBHelper getInstance() {
        if (instance == null)
            instance = new DBHelper();
        return instance;
    }


    public String connect() {
        try {
            connection = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Database connected successfully";
    }



    public String checkIfDbExists(){
        try {
            if (connection == null)
                throw new SQLException("Connection is not found");
            statement = connection.createStatement();

            preparedStatement = connection.prepareStatement("USE matcha");
            resultSet = preparedStatement.executeQuery();

            return resultSet.getCursorName().toString();
            
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public void close() {
        try {
            if (connection != null)
                connection.close();
            
            if (statement != null)
                statement.close();
            
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (resultSet != null) 
                resultSet.close();
        } catch (SQLException e) {

        }

        
    }

}