package com.demo;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        try {
            String resource = "mybatis-config.xml";
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream, "development2");
            //获取SQLSession
            SqlSession openSession = build.openSession();
            UserMapper mapper = openSession.getMapper(UserMapper.class);
            User user = mapper.selectById(1);
            System.out.println("user = " + user);

        } catch (IOException e) {
            System.out.println("加载配置文件失败");
            e.printStackTrace();
        }

    }

}
