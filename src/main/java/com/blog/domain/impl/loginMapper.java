package com.blog.domain.impl;

import com.blog.domain.loginDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface loginMapper {

    @Select("SELECT * FROM account")
    loginDTO getAllAccountData() throws Exception;

    @Select("SELECT * FROM account WHERE uid = #{userID}")
    loginDTO login(String userID) throws Exception;

    @Insert("INSERT INTO account values(null, #{userID}, #{userPassword}, #{gender}")
    loginDTO register(String userID, String userPassword, String gender) throws Exception;
}
