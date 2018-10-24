package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class AgreeWithCommentDtoResponse {

    private String response;
    private String error;

    public AgreeWithCommentDtoResponse(){
    }

    public AgreeWithCommentDtoResponse(String response, String error){
        this.response = response;
        this.error = error;
    }


    public AgreeWithCommentDtoResponse createResponse(String jsonResponse){
        return new Gson().fromJson(jsonResponse, AgreeWithCommentDtoResponse.class);
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
