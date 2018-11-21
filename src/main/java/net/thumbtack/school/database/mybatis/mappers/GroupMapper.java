package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupMapper {

    @Insert("INSERT INTO groups (schoolId, name, room) VALUES " +
            " ( #{school.id}, #{group.name}, #{group.room} ) ")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    public Integer insert(@Param("school") School school,
                          @Param("group") Group group);

    @Update("UPDATE groups SET name = #{name}, room = #{room} WHERE id = #{id} ")
    public void update(Group group);

    @Select("SELECT id, name, room FROM groups ")
    public List<Group> getAll();

    @Select("SELECT id, name, room FROM groups WHERE schoolId = #{school.id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getTraineesByGroupId", fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getSubjectsByGroupId", fetchType = FetchType.LAZY))
    })
    public List<Group> getGroupsBySchoolId(School school);

    @Delete("DELETE FROM groups WHERE id = #{id}")
    public void delete(Group group);

    @Update("UPDATE trainees SET groupId = #{group.id} WHERE id = #{trainee.id}")
    public void moveTraineeToGroup(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Update("UPDATE trainees SET groupId = null WHERE id = #{id}")
    public void deleteTraineeFromGroup(Trainee trainee);

    @Insert("INSERT INTO group_subject (groupId, subjectId) VALUES " +
            " ( #{group.id}, #{subject.id} ) ")
    public void addSubjectToGroup(@Param("group") Group group, @Param("subject") Subject subject);

}
