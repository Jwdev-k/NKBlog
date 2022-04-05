package com.blog.domain.impl;

import com.blog.domain.boardDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;

public class boardDAO implements boardMapper {

    private int limit = 20;

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(boardMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public ArrayList<boardDTO> boardList(int pagenumber, int start) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        if (pagenumber != 1) {
            int n = pagenumber * limit; // limit 20
            start = n - limit;
            return mapper.boardList(n, start);
        }
        return mapper.boardList(20, 0);
    }

    @Override
    public ArrayList<boardDTO> nextpage(int pagenumber) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.nextpage((pagenumber - 1) * limit);
    }

    @Override
    public void addboard(boardDTO bbs) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        mapper.addboard(bbs);
    }

    @Override
    public void deleteboard(int bno) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        mapper.deleteboard(bno);
    }

    @Override
    public void updateboard(boardDTO bbs) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        mapper.updateboard(bbs);
    }

    @Override
    public boardDTO getBbs(int bno) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.getBbs(bno);
    }
}
