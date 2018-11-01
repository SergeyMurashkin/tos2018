package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class AddCommentDtoResponse {

    private String response;
    private String error;

    public AddCommentDtoResponse() {
    }

    public AddCommentDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public AddCommentDtoResponse createResponse(String jsonAddCommentResponse) {
        return new Gson().fromJson(jsonAddCommentResponse, AddCommentDtoResponse.class);
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
