package com.blog.domain.impl;

import com.blog.domain.boardDTO;
import org.apache.ibatis.annotations.Insert;

public interface boardMapper {

    @Insert("INSERT INTO boardlist VALUES(null, #{title}, #{uid}, #{created}, #{content}, #{available})")
    void addboard(boardDTO bbs) throws Exception;
}
