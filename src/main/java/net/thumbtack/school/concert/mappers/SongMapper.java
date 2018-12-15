package net.thumbtack.school.concert.mappers;

import net.thumbtack.school.concert.model.Song;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

public interface SongMapper {

    @Insert("INSERT INTO songs (title, singer, duration, userId) VALUES " +
            " ( #{song.title}, #{song.singer}, #{song.duration}, #{userId} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "song.id")
    void addSong(@Param("song") Song song,
                 @Param("userId") Integer userId);

    @Delete("DELETE FROM songs")
    void deleteAll();

    @Select("SELECT userId FROM songs WHERE id = #{songId}")
    Integer getUserIdBySongId(Integer songId);

    @Delete("DELETE FROM songs WHERE id =#{songId}")
    void deleteSong(Integer songId);

    @Update("UPDATE songs SET userId = #{userId} WHERE id =#{songId}")
    void changeSongUserId(@Param("songId") Integer songId,
                          @Param("userId") Integer userId);

    @Select("SELECT * FROM songs WHERE userId = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "composers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getComposersBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "authors", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getAuthorsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsBySongId", fetchType = FetchType.LAZY))
    })
    List<Song> getSongsByUserId(Integer userId);

    @Select("SELECT * FROM songs")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "composers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getComposersBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "authors", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getAuthorsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsBySongId", fetchType = FetchType.LAZY))
    })
    List<Song> getAllSongs();

    @Select("SELECT * FROM songs WHERE  id = #{songId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "composers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getComposersBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "authors", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getAuthorsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsBySongId", fetchType = FetchType.LAZY))
    })
    Song getSongById(Integer songId);

    @Select("SELECT composer FROM song_composer WHERE  songId = #{songId}")
    List<String> getComposersBySongId(Integer songId);

    @Select("SELECT author FROM song_author WHERE  songId = #{songId}")
    List<String> getAuthorsBySongId(Integer songId);

    @Insert("INSERT INTO song_composer (songId, composer) VALUES " +
            " (#{songId}, #{composer})  ")
    void addComposerToSong(@Param("songId") Integer songId,
                           @Param("composer") String composer);

    @Insert("INSERT INTO song_author (songId, author) VALUES " +
            " (#{songId}, #{author})  ")
    void addAuthorToSong(@Param("songId") Integer songId,
                         @Param("author")String author);

    @Select( {"<script>",
            "SELECT * FROM songs WHERE id IN (SELECT songId FROM song_composer WHERE ",
            "<foreach item='item' collection='list' separator='OR'>",
            " composer=#{item} ",
            "</foreach>",
            " )",
            "</script>" })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "composers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getComposersBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "authors", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getAuthorsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsBySongId", fetchType = FetchType.LAZY))
    })
    List<Song> getComposersSongs(@Param("list") Set<String> composers);

    @Select( {"<script>",
            "SELECT * FROM songs WHERE id IN (SELECT songId FROM song_author WHERE ",
            "<foreach item='item' collection='list' separator='OR'>",
            " author=#{item} ",
            "</foreach>",
            " )",
            "</script>" })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "composers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getComposersBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "authors", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getAuthorsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsBySongId", fetchType = FetchType.LAZY))
    })
    List<Song> getAuthorsSongs(@Param("list") Set<String> authors);

    @Select("SELECT * FROM songs WHERE  singer = #{singer}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "composers", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getComposersBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "authors", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getAuthorsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsBySongId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsBySongId", fetchType = FetchType.LAZY))
    })
    List<Song> getSingerSongs(String singer);
}
