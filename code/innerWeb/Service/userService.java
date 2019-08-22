package com.Service;

import com.Utils.MybatisUtils;
import com.dao.IUserDao;
import com.entity.User;
import org.apache.ibatis.session.SqlSession;

public class userService {
    //负责用户相关业务方法

    public userService() {
    }

    //调用数据库判断
    public User login(String username, String password) {

        SqlSession sqlSession = MybatisUtils.getSession();
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        User user = dao.findUser(username, password);
        sqlSession.close();
        return user;
    }

    //    调用数据库判断是否存在该用户名
    public User isExist(String username) {
        SqlSession sqlSession = MybatisUtils.getSession();
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        User user = dao.isExist(username);
        sqlSession.close();
        return user;
    }

    public void storeUser(String username, String password) {
        SqlSession sqlSession = MybatisUtils.getSession(true);
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        dao.storeUser(username, password);
        sqlSession.close();
    }
//    BUG!!!
// 需要提交
//  全局sqlsession有问题，会导致关闭不再打开


}
