package net.thumbtack.school.concert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.school.concert.adapter.CommentAdapter;
import net.thumbtack.school.concert.adapter.SongAdapter;
import net.thumbtack.school.concert.dao.CommonDao;
import net.thumbtack.school.concert.dao.SongDao;
import net.thumbtack.school.concert.dao.UserDao;
import net.thumbtack.school.concert.daoimpl.CommonDaoImpl;
import net.thumbtack.school.concert.daoimpl.SongDaoImpl;
import net.thumbtack.school.concert.daoimpl.UserDaoImpl;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.server.Server;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestBase {

    private static boolean setUpIsDone = false;
    protected SongDao songDao = new SongDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected Server server = new Server();
    protected Gson gson = new GsonBuilder()
            .registerTypeAdapter(Song.class, new SongAdapter())
            .registerTypeAdapter(Comment.class, new CommentAdapter())
            .create();
    protected CommonDao commonDao = new CommonDaoImpl();


    @BeforeClass()
    public static void setUp() {
        if (!setUpIsDone) {
            Assume.assumeTrue(MyBatisSession.initSqlSessionFactory());
            setUpIsDone = true;
        }
    }

    @Before()
    public void clearDatabase() {
        commonDao.clear();
    }


}
