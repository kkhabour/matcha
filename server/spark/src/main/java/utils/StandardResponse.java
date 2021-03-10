package utils;

import com.google.gson.JsonElement;

public class StandardResponse {

    private StatusResponse status;
    private String message;
    private JsonElement data;


    public StandardResponse(StatusResponse status){
        this.status = status;

    } 

    public StandardResponse(StatusResponse status, String message){
        this(status);
        this.message = message;
    }

    public StandardResponse(StatusResponse status, JsonElement data){
        this(status);
        this.data = data;
    }

    public StandardResponse(StatusResponse status, String message, JsonElement data) {
        this(status, message);
        this.data = data;
    }

    public StatusResponse getStatus() {
        return this.status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getJsonElement() {
        return this.data;
    }

    public void setJsonElement(JsonElement data) {
        this.data = data;
    }

    
}