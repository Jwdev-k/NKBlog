package web.nkblog.service;

import web.nkblog.domain.FileDTO;

public interface FileService {
    void saveFile(FileDTO file) throws Exception;
}
