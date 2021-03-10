package utils;

public enum StatusResponse {
    
    success ("success"), error ("error");

    private String status;

    private StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
    
}