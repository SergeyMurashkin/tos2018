package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.MyBatisSession;
import net.thumbtack.school.concert.mappers.*;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisSession.getSession();
    }

    protected UserMapper getUserMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(UserMapper.class);
    }

    protected SongMapper getSongMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SongMapper.class);
    }

    protected RatingMapper getRatingMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(RatingMapper.class);
    }

    protected CommentMapper getCommentMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(CommentMapper.class);
    }

}