package handlers;

import spark.Request;
import spark.Response;
import utils.CallsStart;
import utils.Endpoints;

import static spark.Spark.get;
import static spark.Spark.post;

public class RootCalls implements CallsStart{


    public RootCalls() {

    }


    @Override
    public void start() {
        get(Endpoints.ROOT, this::root);
    }


    private String root(Request request, Response response) {
        return "root";
    }
    
}