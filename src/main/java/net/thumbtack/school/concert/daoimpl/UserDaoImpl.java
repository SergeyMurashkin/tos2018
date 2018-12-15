package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.dao.UserDao;
import net.thumbtack.school.concert.model.User;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoImpl extends DaoImplBase implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public String registerUser(User user, String token) throws RequestException {
        LOGGER.debug("DAO register User {}.", user);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).registerUser(user, token);
            } catch (PersistenceException ex3) {
                LOGGER.info("Can't register User duplicate {}.", user, ex3);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.LOGIN_DUPLICATE);
            } catch (RuntimeException ex1) {
                LOGGER.info("Can't register User {}.", user, ex1);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_USER_REGISTRATION);
            }
            sqlSession.commit();
        }
        return token;
    }

    @Override
    public String loginUser(String login, String password, String token) throws RequestException {
        LOGGER.debug("DAO login User {}.", login);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).loginUser(login, password, token);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't login User {}.", login, ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_USER_LOGIN);
            }
            sqlSession.commit();
        }
        return token;
    }

    @Override
    public String logoutUser(String token) throws RequestException {
        LOGGER.debug("DAO logout user Token {}.", token);
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).logoutUser(token);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't logout user Token {}.", token, ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_USER_LOGOUT);
            }
            sqlSession.commit();
        }
        return "Successful logout.";
    }

    @Override
    public Integer getUserIdByToken(String token) throws RequestException {
        LOGGER.debug("DAO return user by Token {}.", token);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return user by Token {}.", token, ex);
                throw new RequestException(RequestErrorCode.INVALID_TOKEN);
            }
        }
    }

    @Override
    public User getUserById(Integer userId) throws RequestException {
        LOGGER.debug("DAO return user by Token {}.", userId);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getUserById(userId);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return user by Token {}.", userId, ex);
                throw new RequestException(RequestErrorCode.INVALID_TOKEN);
            }
        }
    }

}
