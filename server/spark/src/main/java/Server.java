
import static spark.Spark.*;

import database.DBHelper;
import routes.Register;

public class Server {

    public static void main(String[] arg) {

        port(80);

        get("/setup", (req, res) -> {
            DBHelper db = DBHelper.getInstance();
            return "database setup";
        });

        get("/", (req, res) -> {

            DBHelper dbHelper = DBHelper.getInstance();

            return "Welcome to Matcha";
        });

        

        get("/register", Register::register);
        

    }
}