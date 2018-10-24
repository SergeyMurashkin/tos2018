package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.model.Song;

public class AddCommentDtoRequest {

    private String token;
    private Song song;
    private String commentText;

    public AddCommentDtoRequest(){
    }

    public AddCommentDtoRequest(String token,
                                Song song,
                                String commentText){
        this.token = token;
        this.song = song;
        this.commentText = commentText;
    }

    public AddCommentDtoRequest createRequest(String  jsonAddComment){
        return new Gson().fromJson(jsonAddComment, AddCommentDtoRequest.class);
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String validate() {
        if(!DataBase.getDatabase().isUserLogged(token)){
            return "error: please login";
        }
        if(!DataBase.getDatabase().isSongSuggested(song)){
            return "error: the song not exists";
        }
        if(commentText.length()>50){
            return "error: more 50 symbols in the comment";
        }else{
            return new Gson().toJson(this, AddCommentDtoRequest.class);
        }

    }
}
