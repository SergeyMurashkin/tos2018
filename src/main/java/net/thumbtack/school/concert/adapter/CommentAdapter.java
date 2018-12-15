package net.thumbtack.school.concert.adapter;

import com.google.gson.*;
import net.thumbtack.school.concert.model.Comment;

import java.lang.reflect.Type;

public class CommentAdapter implements JsonSerializer<Comment> {

    @Override
    public JsonElement serialize(Comment comment, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", comment.getId());
        jsonObject.addProperty("userId", comment.getUserId());
        jsonObject.addProperty("songId", comment.getSongId());
        jsonObject.addProperty("text", comment.getText());
        JsonArray agreedUsersId = new JsonArray();
        jsonObject.add("agreedUsersId", agreedUsersId);
        if(comment.getAgreedUsersId()!=null) {
            for (Integer userId : comment.getAgreedUsersId()) {
                agreedUsersId.add(userId);
            }
        }
        return jsonObject;
    }

}
