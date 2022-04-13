package web.nkblog.domain.impl;

import org.apache.ibatis.annotations.*;
import web.nkblog.domain.boardDTO;

import java.util.ArrayList;

public interface boardMapper {

    @Select("SELECT * FROM ( SELECT * FROM boardlist WHERE Available = 1 LIMIT #{start}, 10 ) sub ORDER BY bno DESC")
    ArrayList<boardDTO> boardList(int start) throws Exception;

    @Insert("INSERT INTO boardlist VALUES(null, #{title}, #{uid}, #{created}, #{content}, #{available})")
    void addboard(boardDTO bbs) throws Exception;

    @Delete("DELETE FROM boardlist WHERE bno = #{bno}")
    void deleteboard(int bno) throws Exception;

    @Update("UPDATE boardlist SET title = #{title}, content = #{content} WHERE bno = #{bno}")
    void updateboard(boardDTO bbs) throws Exception;

    @Select("SELECT COUNT(*) FROM boardlist WHERE Available = 1")
    int countBoardList() throws Exception;

    @Select("SELECT * FROM boardlist WHERE title LIKE #{keyword} AND Available = 1")
    ArrayList<boardDTO> searchBoardTitle(String keyword) throws Exception;

    @Select("SELECT * FROM boardlist WHERE content LIKE #{keyword} AND Available = 1")
    ArrayList<boardDTO> searchBoardContent(String keyword) throws Exception;

    @Select("SELECT * FROM boardlist WHERE uid LIKE #{keyword} AND Available = 1")
    ArrayList<boardDTO> searchBoardUid(String keyword) throws Exception;

    @Select("SELECT * FROM boardlist WHERE bno = #{bno} AND Available = 1")
    boardDTO getBoard(int bno) throws Exception;

    @Select("SELECT EXISTS (SELECT * FROM boardlist WHERE Available = 1 LIMIT #{start}, 10) as success")
    boolean nextPageCheck(int start) throws Exception;
}
