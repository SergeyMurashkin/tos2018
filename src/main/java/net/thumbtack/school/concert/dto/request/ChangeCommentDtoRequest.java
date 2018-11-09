package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

public class ChangeCommentDtoRequest {

    private String token;
    private Integer commentId;
    private Integer songId;
    private String newCommentText;

    public ChangeCommentDtoRequest() {
    }

    public ChangeCommentDtoRequest(String token,
                                   Integer commentId,
                                   Integer songId,
                                   String newCommentText) {
        this.token = token;
        this.commentId = commentId;
        this.songId = songId;
        this.newCommentText = newCommentText;
    }

    public ChangeCommentDtoRequest createRequest(String jsonChangeComment) {
        return new Gson().fromJson(jsonChangeComment, ChangeCommentDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (!DataBase.getDatabase().isCommentExists(commentId)) {
            return "error: the comment not exists";
        }
        if (!DataBase.getDatabase().isYourComment(token, commentId)) {
            return "error: the comment not yours";
        }
        if (newCommentText.length() > 50) {
            return "error: more 50 symbols in the comment";
        } else {
            return new Gson().toJson(this, ChangeCommentDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getSongId(){
        return songId;
    }

    public String getNewCommentText() {
        return newCommentText;
    }



}
