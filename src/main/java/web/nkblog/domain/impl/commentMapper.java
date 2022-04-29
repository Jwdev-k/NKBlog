package web.nkblog.domain.impl;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import web.nkblog.domain.commentDTO;

import java.util.ArrayList;

public interface commentMapper {

    @Select("SELECT * FROM comment WHERE bno = #{bno}")
    commentDTO getComment(commentDTO comment) throws Exception;

    @Select("SELECT * FROM ( SELECT * FROM comment WHERE bno = #{bno} LIMIT #{start}, 10 ) sub ORDER by id DESC")
    ArrayList<commentDTO> commentList(int bno, int start) throws Exception;

    @Insert("INSERT INTO comment VALUES(null, #{bno}, #{uid}, #{comment}, #{created})")
    void addComment(commentDTO comment) throws Exception;

    @Update("UPDATE boardlist SET bno = #{bno}, uid = #{uid} WHERE comment = #{comment}")
    void setComment(commentDTO comment) throws Exception;

    @Delete("DELETE FROM comment WHERE bno = #{bno}")
    void deleteComment(int bno) throws Exception;
}
