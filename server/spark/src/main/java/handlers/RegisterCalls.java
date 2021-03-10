package handlers;

import spark.Request;
import spark.Response;
import utils.CallsStart;
import utils.Endpoints;
import utils.StandardResponse;
import utils.StatusResponse;

import static spark.Spark.*;

import com.google.gson.Gson;

import models.User;



public class RegisterCalls implements CallsStart{
    
    public String register(Request request, Response response) {
        response.type("application/json");

        User user = new Gson().fromJson(request.body(), User.class);
        user.hashPassword();
        return new Gson().toJson(new StandardResponse(StatusResponse.success, new Gson().toJsonTree(user)));
    }

    @Override
    public void start() {
        post(Endpoints.REGISTER, this::register);

    }
}