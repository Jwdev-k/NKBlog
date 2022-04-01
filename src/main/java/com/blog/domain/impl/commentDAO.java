package com.blog.domain.impl;

import com.blog.domain.commentDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class commentDAO implements commentMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(commentMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }


    @Override
    public void addcomment(commentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        mapper.addcomment(comment);
    }
}
