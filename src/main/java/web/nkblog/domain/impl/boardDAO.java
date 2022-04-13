package web.nkblog.domain.impl;

import web.nkblog.domain.boardDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.util.ArrayList;

public class boardDAO implements boardMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(boardMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public ArrayList<boardDTO> boardList(int start) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.boardList(start);
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
    public int countBoardList() throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.countBoardList();
    }

    @Override
    public ArrayList<boardDTO> searchBoardTitle(String keyword) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.searchBoardTitle(keyword);
    }

    @Override
    public ArrayList<boardDTO> searchBoardContent(String keyword) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.searchBoardContent(keyword);
    }

    @Override
    public ArrayList<boardDTO> searchBoardUid(String keyword) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.searchBoardUid(keyword);
    }

    @Override
    public boardDTO getBoard(int bno) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.getBoard(bno);
    }

    @Override
    public boolean nextPageCheck(int start) throws Exception {
        var mapper = getSqlSession().getMapper(boardMapper.class);
        return mapper.nextPageCheck(start);
    }

}
