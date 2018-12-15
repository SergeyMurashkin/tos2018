package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class ChangeCommentDtoRequest {

    private String token;
    private Integer commentId;
    private String newCommentText;

    public ChangeCommentDtoRequest() {
    }

    public ChangeCommentDtoRequest(String token,
                                   Integer commentId,
                                   String newCommentText) {
        this.token = token;
        this.commentId = commentId;
        this.newCommentText = newCommentText;
    }

    public ChangeCommentDtoRequest createRequest(String jsonChangeComment) {
        return new Gson().fromJson(jsonChangeComment, ChangeCommentDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (newCommentText.length() > 50) {
            throw new RequestException(RequestErrorCode.COMMENT_TEXT_LENGTH);
        }
    }

    public String getToken() {
        return token;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getNewCommentText() {
        return newCommentText;
    }

}
