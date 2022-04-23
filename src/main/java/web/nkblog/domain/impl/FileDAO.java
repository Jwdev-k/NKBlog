package web.nkblog.domain.impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import web.nkblog.domain.FileDTO;

import java.io.InputStream;

public class FileDAO implements FileMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(FileMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public void saveFile(FileDTO file) throws Exception {
        var mapper = getSqlSession().getMapper(FileMapper.class);
        getSqlSession().close();
        mapper.saveFile(file);
    }

    @Override
    public FileDTO getFile(int bno) throws Exception {
        var mapper = getSqlSession().getMapper(FileMapper.class);
        getSqlSession().close();
        return mapper.getFile(bno);
    }
}
