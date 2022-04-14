package web.nkblog.service;

import org.springframework.web.multipart.MultipartFile;
import web.nkblog.domain.FileDTO;

public interface FileService {
    void saveFile(String rootDirectory, MultipartFile file) throws Exception;
    FileDTO getFile(int bno) throws Exception;
}
