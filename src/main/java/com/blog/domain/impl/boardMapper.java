package com.blog.domain.impl;

import com.blog.domain.boardDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface boardMapper {

    @Select("SELECT * FROM boardlist WHERE Available = 1 LIMIT 10")
    ArrayList<boardDTO> boardList() throws Exception;

    @Insert("INSERT INTO boardlist VALUES(null, #{title}, #{uid}, #{created}, #{content}, #{available})")
    void addboard(boardDTO bbs) throws Exception;

    @Delete("DELETE FROM boardlist WHERE bno = #{bno}")
    void deleteboard(int bno) throws Exception;
}
