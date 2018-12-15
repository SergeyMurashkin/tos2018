package net.thumbtack.school.concert.mappers;

import net.thumbtack.school.concert.model.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Set;

public interface CommentMapper {

    @Insert("INSERT INTO comments (songId, userId, text) VALUES " +
            " ( #{comment.songId}, #{comment.userId}, #{comment.text} ) ")
    @Options(useGeneratedKeys = true,  keyProperty = "comment.id")
    void addComment(@Param("comment") Comment comment);

    @Update("UPDATE comments SET text = #{text} WHERE " +
            "id = #{commentId} AND userId = #{userId}")
    void changeComment(@Param("commentId") Integer commentId,
                       @Param("userId") Integer userId,
                       @Param("text") String text);

    @Select("SELECT EXISTS(SELECT id FROM comment_agreedUser WHERE commentId=#{commentId} AND userId=#{userId} )")
    boolean isAgreementExists(@Param("commentId") Integer commentId,
                              @Param("userId") Integer userId);

    @Delete("DELETE FROM comment_agreedUser WHERE commentId=#{commentId} AND userId=#{userId}")
    void disagreeWithComment(@Param("commentId") Integer commentId,
                             @Param("userId") Integer userId);

    @Insert("INSERT INTO comment_agreedUser (commentId, userId) VALUES " +
            " ( #{commentId}, #{userId} )" )
    void agreeWithComment(@Param("commentId") Integer commentId,
                          @Param("userId") Integer userId);

    @Select("SELECT * FROM comments WHERE songId = #{songId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "agreedUsersId", column = "id", javaType = Set.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getAgreedUsersIdByCommentId", fetchType = FetchType.LAZY))
    })
    List<Comment> getCommentsBySongId(Integer songId);

    @Select("SELECT * FROM comments WHERE userId = #{userId}")
   /* @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "agreedUsersId", column = "id", javaType = Set.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getAgreedUsersIdByCommentId", fetchType = FetchType.LAZY))
    })*/
    List<Comment> getCommentsByUserId(Integer userId);

    @Select("SELECT * FROM comments WHERE userId IN (SELECT commentId FROM comment_agreedUser WHERE userId = #{userId})" )
  /*  @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "agreedUsersId", column = "id", javaType = Set.class,
                    many = @Many(select = "net.thumbtack.school.concert.mappers.CommentMapper.getAgreedUsersIdByCommentId", fetchType = FetchType.LAZY))
    })*/
    List<Comment> getCommentsByAgreedUserId(Integer userId);

    @Select("SELECT userId FROM comment_agreedUser WHERE commentId=#{commentId}")
    Set<Integer> getAgreedUsersIdByCommentId(Integer commentId);


}
