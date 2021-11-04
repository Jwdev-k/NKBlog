package com.blog.domain.impl;

import com.blog.domain.loginDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


public class loginDAO implements loginMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(loginMapper.class);
        return sqlSessionFactory.openSession();
    }

    @Override
    public loginDTO getAllAccountData() {
        loginDTO dto = null;
        try {
            var mapper = getSqlSession().getMapper(loginMapper.class);
            dto = mapper.getAllAccountData();
        } catch (Exception e) {
            System.out.println("데이터를 찾을수없음");
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public loginDTO login(String userID) {
        loginDTO dto = null;
        try {
            var mapper = getSqlSession().getMapper(loginMapper.class);
            dto = mapper.login(userID);
            return dto;
        } catch (Exception e) {
            System.out.println("데이터를 찾을수없음");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public loginDTO register(String userID, String userPassword, String gender) throws Exception {
        var mapper = getSqlSession().getMapper(loginMapper.class);
        return mapper.register(userID,userPassword,gender);
    }


}
