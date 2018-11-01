package net.thumbtack.school.concert.dto.response;

public class LeaveServerDtoResponse {

    private String response;
    private String error;

    public LeaveServerDtoResponse() {
    }

    public LeaveServerDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
