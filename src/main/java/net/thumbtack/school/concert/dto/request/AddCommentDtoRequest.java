package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class AddCommentDtoRequest {

    private String token;
    private Integer songId;
    private String text;

    public AddCommentDtoRequest() {
    }

    public AddCommentDtoRequest(String token,
                                Integer songId,
                                String text) {
        this.token = token;
        this.songId = songId;
        this.text = text;
    }

    public AddCommentDtoRequest createRequest(String jsonAddComment) {
        return new Gson().fromJson(jsonAddComment, AddCommentDtoRequest.class);
    }

    public String getToken() {
        return token;
    }

    public Integer getSongId() {
        return songId;
    }

    public String getText() {
        return text;
    }

    public void validate() throws RequestException {
        if (text.length() > 50) {
            throw new RequestException(RequestErrorCode.COMMENT_TEXT_LENGTH);
        }
    }

}
