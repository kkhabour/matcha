
import static spark.Spark.*;

import handlers.*;

public class Server {
    
    public static void main(String[] arg) {

        port(80);


        RootCalls rootCalls = new RootCalls();
        rootCalls.start();

        RegisterCalls registerCalls = new RegisterCalls();
        registerCalls.start();


        LoginCalls loginCalls = new LoginCalls();
        loginCalls.start();
        

        

    }
}