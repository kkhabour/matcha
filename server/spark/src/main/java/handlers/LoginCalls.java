package handlers;


import static spark.Spark.post;
import static spark.Spark.get;

import spark.Request;
import spark.Response;
import utils.CallsStart;
import utils.Endpoints;


public class LoginCalls implements CallsStart{
    


    public LoginCalls() {

    }

    @Override
    public void start() {
        get(Endpoints.LOGIN, this::login);
        post(Endpoints.LOGIN, this::login);
    }

    private String login(Request request, Response response) {
        return Endpoints.LOGIN;
    }
}