package com.blog.domain.impl;

import com.blog.domain.commentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface commentMapper {

    @Insert("INSERT INTO comment VALUES(#{bno}, #{uid}, #{comment})")
    void addcomment(commentDTO comment) throws Exception;
}
