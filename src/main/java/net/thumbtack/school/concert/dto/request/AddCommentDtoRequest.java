package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

public class AddCommentDtoRequest {

    private String token;
    private Integer songId;
    private String commentText;

    public AddCommentDtoRequest() {
    }

    public AddCommentDtoRequest(String token,
                                Integer songId,
                                String commentText) {
        this.token = token;
        this.songId = songId;
        this.commentText = commentText;
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

    public String getCommentText() {
        return commentText;
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (!DataBase.getDatabase().isSongSuggested(songId)) {
            return "error: the song not exists";
        }
        if (commentText.length() > 50) {
            return "error: more 50 symbols in the comment";
        } else {
            return new Gson().toJson(this, AddCommentDtoRequest.class);
        }
    }

}
