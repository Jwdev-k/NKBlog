package web.nkblog.domain.impl;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import web.nkblog.domain.FileDTO;

public interface FileMapper {
    @Insert("INSERT INTO file VALUES(#{bno}, #{filename}, #{data})")
    void saveFile(FileDTO file) throws Exception;

    @Select("SELECT * FROM file WHERE bno = #{bno}")
    FileDTO getFile(int bno) throws Exception;
}
