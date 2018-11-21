package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {

    @Insert("INSERT INTO subjects (name) VALUES " +
            "( #{name} )")
    @Options(useGeneratedKeys = true)
    public Integer insert(Subject subject);

    @Delete("DELETE FROM subjects")
    public void deleteAll();

    @Select("SELECT id, name FROM subjects WHERE id = #{id}")
    public Subject getById(int id);

    @Update("UPDATE subjects SET name = #{name} WHERE id = #{id}")
    public void update(Subject subject);

    @Delete("DELETE FROM subjects WHERE id = #{id}")
    public void delete(Subject subject);

    @Select("SELECT id, name FROM subjects")
    public List<Subject> getAll();

    @Select("SELECT id, name FROM subjects WHERE id IN (SELECT subjectId FROM group_subject WHERE groupId = #{group.id}) ")
    public List<Subject> getSubjectsByGroupId(Group group);

}
