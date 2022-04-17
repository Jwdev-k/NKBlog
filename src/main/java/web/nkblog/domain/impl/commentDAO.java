package web.nkblog.domain.impl;

import web.nkblog.domain.commentDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;

public class commentDAO implements commentMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(commentMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public commentDTO getComment(commentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        return mapper.getComment(comment);
    }

    @Override
    public ArrayList<commentDTO> commentList(int bno, int start) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        return mapper.commentList(bno, start);
    }

    @Override
    public void addComment(commentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        mapper.addComment(comment);
    }

    @Override
    public void setComment(commentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        mapper.setComment(comment);
    }

    @Override
    public void deleteComment(int bno) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        mapper.deleteComment(bno);
    }
}
