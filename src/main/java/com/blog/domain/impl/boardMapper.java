package com.blog.domain.impl;

import com.blog.domain.boardDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface boardMapper {

    @Select("SELECT * FROM boardlist WHERE bno > #{pagenumber}  AND Available = 1 LIMIT 20")
    ArrayList<boardDTO> boardList(int pagenumber) throws Exception;

    @Select("SELECT * FROM boardlist WHERE bno > #{pagenumber}  AND Available = 1")
    ArrayList<boardDTO> nextpage(int pagenumber) throws Exception;

    @Insert("INSERT INTO boardlist VALUES(null, #{title}, #{uid}, #{created}, #{content}, #{available})")
    void addboard(boardDTO bbs) throws Exception;

    @Delete("DELETE FROM boardlist WHERE bno = #{bno}")
    void deleteboard(int bno) throws Exception;
}
