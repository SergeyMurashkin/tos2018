package net.thumbtack.school.concert.mappers;

import net.thumbtack.school.concert.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO users (firstName, lastName, login, password, token) VALUES " +
            " ( #{user.firstName}, #{user.lastName}, #{user.login}, #{user.password}, #{token} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void registerUser(@Param("user") User user,
                      @Param("token") String token);

    @Delete("DELETE FROM users")
    void deleteAll();

    @Delete("DELETE FROM users WHERE login NOT LIKE 'WhiteSailsCommunity@gmail.com'")
    void deleteAllWithoutCommunity();

    @Update("UPDATE users SET token=#{token} WHERE login=#{login} AND password=#{password}")
    void loginUser(@Param("login") String login,
                   @Param("password") String password,
                   @Param("token") String token);

    @Update("UPDATE users SET token=null WHERE token=#{token}")
    void logoutUser(@Param("token") String token);

    @Select("SELECT id, firstName, lastName, login, password FROM users WHERE token=#{token}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "suggestedSongs", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getSongsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "agreedComments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsByAgreedUserId", fetchType = FetchType.LAZY))
    })
    User getUserByToken(String token);

    @Select("SELECT id, firstName, lastName, login, password FROM users WHERE id=#{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "suggestedSongs", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.SongMapper.getSongsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "ratings", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.RatingMapper.getRatingsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "comments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "agreedComments", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getCommentsByAgreedUserId", fetchType = FetchType.LAZY))
    })
    User getUserById(Integer userId);

    @Select("SELECT id FROM users WHERE token=#{token}")
    Integer getUserIdByToken(String token);

    @Delete("DELETE FROM users WHERE id=#{userId}")
    void deleteUserById(Integer userId);
}
