package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.dao.SchoolDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SchoolDaoImpl extends DaoImplBase implements SchoolDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDaoImpl.class);

    @Override
    public School insert(School school) {
        LOGGER.debug("DAO insert School {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).insert(school);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert School. {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return school;
    }

    @Override
    public School getById(int id) {
        LOGGER.debug("DAO get School by Id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getSchoolMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get School with Id. {}", id, ex);
            throw ex;
        }
    }

    @Override
    public List<School> getAllLazy() {
        LOGGER.debug("DAO get all Schools LAZY");
        try (SqlSession sqlSession = getSession()) {
            return getSchoolMapper(sqlSession).getAllLazy();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all Schools LAZY. {}", ex);
            throw ex;
        }
    }

    @Override
    public List<School> getAllUsingJoin() {
        LOGGER.debug("DAO get all Schools JOIN");
        try (SqlSession sqlSession = getSession()) {
            return sqlSession.selectList("net.thumbtack.school.database.mybatis.mappers.SchoolMapper.getAllUsingJoin");
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all Schools JOIN. {}", ex);
            throw ex;
        }
    }

    @Override
    public void update(School school) {
        LOGGER.debug("DAO update School {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).update(school);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update School. {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void delete(School school) {
        LOGGER.debug("DAO delete School {}", school);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).delete(school);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete School {}. {}",school, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Schools");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all Subjects. {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public School insertSchoolTransactional(School school2018) {
        LOGGER.debug("DAO transactional insert School {}", school2018);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSchoolMapper(sqlSession).insert(school2018);
                List<Group> groups = school2018.getGroups();
                for(Group group: groups){
                    getGroupMapper(sqlSession).insert(school2018, group);
                    List<Trainee> trainees = group.getTrainees();
                    for(Trainee trainee: trainees){
                        getTraineeMapper(sqlSession).insert(group, trainee);
                        getGroupMapper(sqlSession).moveTraineeToGroup(group, trainee);
                    }
                    List<Subject> subjects = group.getSubjects();
                    for(Subject subject: subjects){
                        getGroupMapper(sqlSession).addSubjectToGroup(group, subject);
                    }
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't transactional insert School. {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return school2018;
    }
}
