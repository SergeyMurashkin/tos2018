package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SchoolMapper {

    @Insert("INSERT INTO schools (name, year) VALUES " +
            "( #{name}, #{year} )")
    @Options(useGeneratedKeys = true)
    Integer insert(School school);

    @Select("SELECT id, name, year FROM schools WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getGroupsBySchoolId", fetchType = FetchType.LAZY))
    })
    School getById(int id);

    @Update("UPDATE schools SET name=#{name}, year=#{year} WHERE id=#{id}")
    void update(School school);

    @Delete("DELETE FROM schools WHERE id = #{id}")
    void delete(School school);

    @Delete("DELETE FROM schools")
    void deleteAll();

    @Select("SELECT id, name, year FROM schools")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getGroupsBySchoolId", fetchType = FetchType.LAZY))
    })
    List<School> getAllLazy();



}
