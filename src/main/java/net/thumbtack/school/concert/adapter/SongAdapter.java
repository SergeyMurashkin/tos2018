package net.thumbtack.school.concert.adapter;

import com.google.gson.*;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Rating;
import net.thumbtack.school.concert.model.Song;

import java.lang.reflect.Type;

public class SongAdapter implements JsonSerializer<Song> {

    @Override
    public JsonElement serialize(Song song, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", song.getId());
        jsonObject.addProperty("title", song.getTitle());
        JsonArray composers = new JsonArray();
        jsonObject.add("composers", composers);
        for (String composer : song.getComposers()) {
            composers.add(composer);
        }
        JsonArray authors = new JsonArray();
        jsonObject.add("authors", authors);
        for (String author : song.getAuthors()) {
            authors.add(author);
        }
        jsonObject.addProperty("singer", song.getSinger());
        jsonObject.addProperty("duration", song.getDuration());
        jsonObject.addProperty("userId", song.getUserId());
        JsonArray ratings = new JsonArray();
        jsonObject.add("ratings", ratings);
        for (Rating rating : song.getRatings()) {
            ratings.add(jsc.serialize(rating, Rating.class));
        }
        JsonArray comments = new JsonArray();
        jsonObject.add("comments", comments);
        if(song.getComments()!=null) {
            for (Comment comment : song.getComments()) {
                comments.add(jsc.serialize(comment, Comment.class));
            }
        }

        return jsonObject;
    }


}
