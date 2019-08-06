package com.derek.demo.SelfBatis;

import com.derek.demo.SelfBatis.Dao.UserMapper;
import com.derek.demo.SelfBatis.Entity.User;
import com.derek.demo.SelfBatis.Mybatis.FactoryBuilder;
import com.derek.demo.SelfBatis.Mybatis.Session;
import com.derek.demo.SelfBatis.Mybatis.SessionFactory;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {

    @Test
    public void test(){
        //得到factorybuilder类
        FactoryBuilder builder = new FactoryBuilder();
        InputStream in = TestMyBatis.class.getResourceAsStream("/M1/SelfBatis/sqlMapConfig.xml");
        SessionFactory sf = builder.build(in);
        Session session = sf.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> allUsers = mapper.findAllUsers();
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }

    }

    //read如果选择url参数，那么最好是绝对路径的
    @Test
    public void test02(){
        SAXReader reader = new SAXReader();
        try {
            reader.read("/M1/SelfBatis/sqlMapConfig.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
