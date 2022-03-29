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

    private static final Logger log = LoggerFactory.getLogger(boardDAO.class);

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(boardMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public ArrayList<boardDTO> boardList(int pagenumber) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        if (pagenumber != 1) {
            int n = (pagenumber - 1) * 20;
            log.debug(n + "/Page level");
            return mapper.boardList(n);
        }
        return mapper.boardList(0);
    }

    @Override
    public ArrayList<boardDTO> nextpage(int pagenumber) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.nextpage(pagenumber);
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
