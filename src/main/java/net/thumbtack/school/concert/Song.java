package net.thumbtack.school.concert;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashSet;

public class Song implements Serializable {

    private static final long serialVersionUID = 1653987098914607270L;
    private String title;
    private HashSet<String> composer;
    private HashSet<String> author;
    private String singer;
    private int duration;

    public Song(){
    }

    public Song(String title,
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



    public Song createSong(String checkedSong) {
        return new Gson().fromJson(checkedSong,Song.class);
    }
}
