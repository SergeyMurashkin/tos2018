package net.thumbtack.school.concert.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Set;

public class Song implements Serializable {

    private static final long serialVersionUID = 1653987098914607270L;
    private String title;
    private Set<String> composer;
    private Set<String> author;
    private String singer;
    private int duration;

    public Song() {
    }

    public Song(String title,
                Set<String> composer,
                Set<String> author,
                String singer,
                int duration) {
        this.title = title.trim();
        this.composer = composer;
        this.author = author;
        this.singer = singer.trim();
        this.duration = duration;
    }

    public Song createSong(String jsonCheckedSong) {
        return new Gson().fromJson(jsonCheckedSong, Song.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        return singer != null ? singer.equals(song.singer) : song.singer == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (singer != null ? singer.hashCode() : 0);
        return result;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getComposer() {
        return composer;
    }

    public Set<String> getAuthor() {
        return author;
    }

    public String getSinger() {
        return singer;
    }

    public int getDuration() {
        return duration;
    }

}
