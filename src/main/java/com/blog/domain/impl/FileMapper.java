package com.blog.domain.impl;

import com.blog.domain.FileDTO;
import org.apache.ibatis.annotations.Insert;

public interface FileMapper {
    @Insert("INSERT INTO file VALUES(#{filename}, #{data})")
    void saveFile(FileDTO file) throws Exception;
}
