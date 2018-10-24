package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.model.Song;

public class ChangeCommentDtoRequest {

    private String token;


    private Song song;
    private int oldCommentIndex;
    private String newCommentText;

    public ChangeCommentDtoRequest() {
    }

    public ChangeCommentDtoRequest(String token,
                                   Song song,
                                   int oldCommentIndex,
                                   String newCommentText) {
        this.token = token;
        this.song = song;
        this.oldCommentIndex = oldCommentIndex;
        this.newCommentText = newCommentText;
    }

    public ChangeCommentDtoRequest createRequest(String jsonChangeComment) {
        return new Gson().fromJson(jsonChangeComment, ChangeCommentDtoRequest.class);
    }


    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (!DataBase.getDatabase().isSongSuggested(song)) {
            return "error: the song not exists";
        }
        if(!DataBase.getDatabase().isCommentExists(song, oldCommentIndex)){
            return "error: the comment not exists";
        }
        if (!DataBase.getDatabase().isYourComment(token, song, oldCommentIndex)){
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

    public void setToken(String token) {
        this.token = token;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getOldCommentIndex() {
        return oldCommentIndex;
    }

    public void setOldCommentIndex(int oldCommentIndex) {
        this.oldCommentIndex = oldCommentIndex;
    }

    public String getNewCommentText() {
        return newCommentText;
    }

    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }
}
