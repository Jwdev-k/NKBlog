package com.blog.domain.impl;

import com.blog.domain.boardDTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface boardMapper {

    @Select("SELECT * FROM boardlist WHERE bno <= #{pagenumber} AND bno > #{start} AND Available = 1 ORDER BY bno DESC LIMIT 20")
    ArrayList<boardDTO> boardList(@Param("pagenumber")int pagenumber, @Param("start")int start) throws Exception;

    @Select("SELECT * FROM boardlist WHERE bno > #{pagenumber} AND Available = 1")
    ArrayList<boardDTO> nextpage(int pagenumber) throws Exception;

    @Insert("INSERT INTO boardlist VALUES(null, #{title}, #{uid}, #{created}, #{content}, #{available})")
    void addboard(boardDTO bbs) throws Exception;

    @Delete("DELETE FROM boardlist WHERE bno = #{bno}")
    void deleteboard(int bno) throws Exception;

    @Update("UPDATE boardlist SET title = #{title}, content = #{content} WHERE bno = #{bno}")
    void updateboard(boardDTO bbs) throws Exception;

    @Select("SELECT * FROM boardlist WHERE bno = #{bno}")
    boardDTO getBbs (int bno) throws Exception;

}
