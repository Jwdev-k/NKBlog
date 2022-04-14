package web.nkblog.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void saveFile(String rootDirectory, MultipartFile file) throws Exception;
}
