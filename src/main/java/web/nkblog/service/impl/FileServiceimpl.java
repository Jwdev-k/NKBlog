package web.nkblog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.nkblog.domain.FileDTO;
import web.nkblog.domain.impl.FileDAO;
import web.nkblog.service.FileService;

import java.io.File;

@Service
@Slf4j
public class FileServiceimpl implements FileService {

    private static FileDAO fd = new FileDAO();
    @Autowired
    boardServiceimpl bbs;

    @Override
    public void saveFile(String rootDirectory, MultipartFile file) {
        File Folder = new File(rootDirectory + "resources\\images");
        if (!Folder.exists()) {
            try {
                Folder.mkdir();
                log.info("폴더를 생성하였습니다.");
            } catch (Exception e) {
                throw new RuntimeException("create folder failed.", e);
            }
        }
            try {
                fd.saveFile(new FileDTO(bbs.lastBno(), file.getOriginalFilename(), file.getBytes()));
                file.transferTo(new File(rootDirectory + "resources\\images\\" + file.getOriginalFilename()));
            } catch (Exception e) {
                throw new RuntimeException("Image saving failed", e);
            }
    }
}
