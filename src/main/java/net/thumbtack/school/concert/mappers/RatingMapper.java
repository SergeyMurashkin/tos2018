package net.thumbtack.school.concert.mappers;

import net.thumbtack.school.concert.model.Rating;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

public interface RatingMapper {

    @Insert("INSERT INTO ratings (songId, userId, rating) VALUES " +
            " ( #{songId}, #{userId}, #{rating} ) ")
    @Options(useGeneratedKeys = true)
    void addRating(@Param("songId") Integer songId,
                   @Param("userId") Integer userId,
                   @Param("rating") Integer rating);

    @Update("UPDATE ratings SET rating = #{rating} WHERE songId = #{songId} AND userId = #{userId}")
    void changeRating(@Param("songId") Integer songId,
                      @Param("userId") Integer userId,
                      @Param("rating") Integer rating);

    @Delete("DELETE FROM ratings WHERE songId = #{songId} AND userId = #{userId}")
    void deleteRating(@Param("userId") Integer userId,
                      @Param("songId") Integer songId);

    @Select("SELECT id FROM ratings WHERE songId = #{songId}")
    Set<Integer> getSongRatingsId(Integer songId);

    @Select("SELECT * FROM ratings WHERE songId = #{songId}")
    List<Rating> getRatingsBySongId(Integer songId);

    @Select("SELECT * FROM ratings WHERE userId = #{userId}")
    List<Rating> getRatingsByUserId(Integer userId);
}
