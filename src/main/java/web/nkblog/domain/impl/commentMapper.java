package web.nkblog.domain.impl;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import web.nkblog.domain.commentDTO;

public interface commentMapper {

    @Select("SELECT * FROM comment WHERE bno = #{bno}")
    commentDTO getComment(commentDTO comment) throws Exception;

    @Insert("INSERT INTO comment VALUES(#{bno}, #{uid}, #{comment})")
    void addComment(commentDTO comment) throws Exception;

    @Update("UPDATE boardlist SET bno = #{bno}, uid = #{uid} WHERE comment = #{comment}")
    void setComment(commentDTO comment) throws Exception;

    @Delete("DELETE FROM comment WHERE bno = #{bno}")
    void deleteComment(int bno) throws Exception;
}
