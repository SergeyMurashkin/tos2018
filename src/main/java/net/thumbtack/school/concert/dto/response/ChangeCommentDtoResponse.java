package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class ChangeCommentDtoResponse {

    private String response;
    private String error;

    public ChangeCommentDtoResponse() {
    }

    public ChangeCommentDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public ChangeCommentDtoResponse createResponse(String jsonChangeComment) {
        return new Gson().fromJson(jsonChangeComment, ChangeCommentDtoResponse.class);
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
