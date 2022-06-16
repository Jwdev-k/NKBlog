package web.nkblog.domain.impl;

import web.nkblog.domain.loginDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface loginMapper {

    @Select("SELECT * FROM account")
    ArrayList<loginDTO> getAllAccountData() throws Exception;

    @Select("SELECT * FROM account WHERE uid = #{userID}")
    loginDTO getAccountData(String uid) throws Exception;

    @Select("SELECT email FROM account WHERE email = #{email}")
    String getUserEmail(String email) throws Exception;

    @Select("SELECT * FROM account WHERE uid = #{userID} AND password = #{password}")
    loginDTO login(@Param("userID") String userID, @Param("password") String password) throws Exception;

    @Insert("INSERT INTO account VALUES(#{uid}, #{password}, #{email}, #{gender})")
    void register(loginDTO user) throws Exception;

    @Update("UPDATE account SET password = #{password2} WHERE uid = #{uid} AND password = #{password}")
    boolean setPassword(@Param("uid") String id, @Param("password") String password, @Param("password2") String password2) throws Exception;
}
