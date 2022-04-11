package com.blog.domain.impl;

import com.blog.domain.loginDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;

public class loginDAO implements loginMapper {

    private static final Logger log = LoggerFactory.getLogger(loginDAO.class);

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(loginMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public ArrayList<loginDTO> getAllAccountData() {
        ArrayList<loginDTO> dto = null;
        try {
            var mapper = getSqlSession().getMapper(loginMapper.class);
            dto = mapper.getAllAccountData();
            getSqlSession().getConnection().close();
        } catch (Exception e) {
            log.debug("데이터를 찾을수없음");
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public loginDTO getAccountData(String uid) throws Exception {
        var mapper = getSqlSession().getMapper(loginMapper.class);
        return mapper.getAccountData(uid);
    }

    @Override
    public loginDTO login(String userID, String password) {
        try {
            var mapper = getSqlSession().getMapper(loginMapper.class);
            loginDTO dto = mapper.login(userID, password);
            getSqlSession().getConnection().close();
            return dto;
        } catch (Exception e) {
            log.debug("데이터를 찾을수없음");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void register(String uid, String password, String gender) throws Exception {
        var mapper = getSqlSession().getMapper(loginMapper.class);
        mapper.register(uid,password,gender);
        var user = new loginDTO(uid, password, gender);
        log.debug(user.toString());
    }

    @Override
    public boolean setPassword(String id, String password, String password2) throws Exception {
        var mapper = getSqlSession().getMapper(loginMapper.class);
        return mapper.setPassword(id,password,password2);
    }


}
