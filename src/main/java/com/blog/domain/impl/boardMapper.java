package com.blog.domain.impl;

import com.blog.domain.boardDTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface boardMapper {

    @Select("SELECT * FROM ( SELECT * FROM boardlist WHERE Available = 1 LIMIT #{start}, 10 ) sub ORDER BY bno DESC")
    ArrayList<boardDTO> boardList(int start) throws Exception;

    @Select("SELECT * FROM boardlist WHERE bno > #{start} AND Available = 1")
    ArrayList<boardDTO> nextpage(int start) throws Exception;

    @Insert("INSERT INTO boardlist VALUES(null, #{title}, #{uid}, #{created}, #{content}, #{available})")
    void addboard(boardDTO bbs) throws Exception;

    @Delete("DELETE FROM boardlist WHERE bno = #{bno}")
    void deleteboard(int bno) throws Exception;

    @Update("UPDATE boardlist SET title = #{title}, content = #{content} WHERE bno = #{bno}")
    void updateboard(boardDTO bbs) throws Exception;

    @Select("SELECT * FROM boardlist WHERE bno = #{bno}")
    boardDTO getBoard (int bno) throws Exception;

    @Select("SELECT COUNT(*) FROM boardlist WHERE Available = 1")
    int countBoardList() throws Exception;

}
