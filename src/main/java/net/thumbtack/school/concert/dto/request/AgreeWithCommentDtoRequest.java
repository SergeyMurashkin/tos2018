package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class AgreeWithCommentDtoRequest {

    private String token;
    private Integer commentId;

    public AgreeWithCommentDtoRequest() {
    }

    public AgreeWithCommentDtoRequest(String token,
                                      Integer commentId) {
        this.token = token;
        this.commentId = commentId;
    }

    public AgreeWithCommentDtoRequest createRequest(String jsonAgreeWithComment) {
        return new Gson().fromJson(jsonAgreeWithComment, AgreeWithCommentDtoRequest.class);
    }

    public void validate() throws RequestException {
    }

    public String getToken() {
        return token;
    }

    public int getCommentId() {
        return commentId;
    }


}
