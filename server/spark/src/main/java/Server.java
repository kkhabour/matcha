

import static spark.Spark.*;


import java.sql.*;

public class Server {
        
    public static void main(String[] arg){
        
        port(80);

        get("/setup", (req, res) -> {
            DBHelper db = new DBHelper();
            db.connect();

            return db.checkIfDbExists();
        });


        get("/", (req, res) -> {
            return "Welcome";
        });



    }



}
