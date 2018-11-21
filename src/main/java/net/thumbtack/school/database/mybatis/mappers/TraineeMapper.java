package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TraineeMapper {


    @Insert("INSERT INTO trainees (groupId, firstName, lastName, rating) VALUES " +
            " ( #{group.id}, #{trainee.firstName}, #{trainee.lastName}, #{trainee.rating} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    public Integer insert(@Param("group") Group group,
                          @Param("trainee") Trainee trainee);

    @Select("SELECT id, firstName, lastName, rating FROM trainees WHERE id = #{id}")
    public Trainee getById(int id);

    @Select("SELECT id, firstName, lastName, rating FROM trainees")
    public List<Trainee> getAll();

    @Update("UPDATE trainees SET firstName = #{firstName}, lastName = #{lastName}, rating = #{rating} WHERE id = #{id}")
    public void update(Trainee trainee);

    @Select({"<script>",
            "SELECT id, firstName, lastName, rating FROM trainees",
            "<where>" +
                    "<if test = 'firstName!=null'> firstName like #{firstName}",
            "</if>",
                    "<if test = 'lastName!=null'> AND lastName like #{lastName}",
            "</if>",
                    "<if test = 'rating!=null'> AND rating like #{rating}",
            "</if>",
            "</where>" +
                    "</script>"})
    public List<Trainee> getAllWithParams(@Param("firstName") String firstName,
                                          @Param("lastName") String lastName,
                                          @Param("rating") Integer rating);

    @Delete("DELETE FROM trainees WHERE id = #{id}")
    public void delete(Trainee trainee);

    @Delete("DELETE FROM trainees")
    public void deleteAll();

    @Select("SELECT id, firstName, lastName, rating FROM trainees WHERE " +
            " groupId = #{group.id}" )
    public List<Trainee> getTraineesByGroupId(Group group);
}
