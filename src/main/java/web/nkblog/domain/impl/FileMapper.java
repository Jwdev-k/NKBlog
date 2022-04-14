package web.nkblog.domain.impl;

import org.apache.ibatis.annotations.Insert;
import web.nkblog.domain.FileDTO;

public interface FileMapper {
    @Insert("INSERT INTO file VALUES(#{bno}, #{filename}, #{data})")
    void saveFile(FileDTO file) throws Exception;
}
