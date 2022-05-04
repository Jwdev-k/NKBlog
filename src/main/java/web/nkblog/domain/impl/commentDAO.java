package web.nkblog.domain.impl;

import org.springframework.stereotype.Repository;
import web.nkblog.domain.CommentDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;

@Repository
public class commentDAO implements commentMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(commentMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public CommentDTO getComment(CommentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        getSqlSession().close();
        return mapper.getComment(comment);
    }

    @Override
    public ArrayList<CommentDTO> commentList(int bno, int start) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        getSqlSession().close();
        return mapper.commentList(bno, start);
    }

    @Override
    public void addComment(CommentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        getSqlSession().close();
        mapper.addComment(comment);
    }

    @Override
    public void setComment(CommentDTO comment) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        getSqlSession().close();
        mapper.setComment(comment);
    }

    @Override
    public void deleteComment(int bno) throws Exception {
        var mapper = getSqlSession().getMapper(commentMapper.class);
        getSqlSession().close();
        mapper.deleteComment(bno);
    }
}
