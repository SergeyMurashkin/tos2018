package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashSet;

public class SuggestSongDtoRequest implements Serializable {

    private static final long serialVersionUID = 1302537092180883494L;
    private String title;
    private HashSet<String> composer;
    private HashSet<String> author;
    private String singer;
    private int duration;

    public SuggestSongDtoRequest(){
    }

    public SuggestSongDtoRequest(String title,
                                 HashSet<String> composer,
                                 HashSet<String> author,
                                 String singer,
                                 int duration){
        this.title = title;
        this.composer = composer;
        this.author = author;
        this.singer = singer;
        this.duration = duration;
    }

    public SuggestSongDtoRequest createSugSongDto(String jsonSong) {
        return new Gson().fromJson(jsonSong, SuggestSongDtoRequest.class);
    }

    public String validate() {
        if(title==null||composer==null||composer.size()==0
                ||author==null||author.size()==0
                ||singer==null||duration<=0){
            return "error";
        }else {
            return new Gson().toJson(this,SuggestSongDtoRequest.class);
        }
    }
}
