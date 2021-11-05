package com.blog.domain.impl;

import com.blog.domain.loginDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface loginMapper {

    @Select("SELECT * FROM account")
    loginDTO getAllAccountData() throws Exception;

    @Select("SELECT * FROM account WHERE uid = #{userID}")
    loginDTO login(String userID) throws Exception;

    @Insert("INSERT INTO account VALUES(null, #{uid}, #{password}, #{gender})")
    void register(@Param("uid") String uid, @Param("password") String password, @Param("gender") String gender) throws Exception;
}
