package web.nkblog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import web.nkblog.domain.FileDTO;
import web.nkblog.domain.impl.FileDAO;
import web.nkblog.service.FileService;

import java.io.File;

@Service
@Slf4j
public class FileServiceimpl implements FileService {

    @Autowired
    private FileDAO fd;
    @Autowired
    private boardServiceimpl bbs;

    @Override
    @Transactional
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
                fd.saveFile(new FileDTO(bbs.lastBno(), file.getOriginalFilename(), file.getBytes()));//데이터베이스에 저장
                //file.transferTo(new File(rootDirectory + "resources\\images\\" + file.getOriginalFilename())); //디스크에 저장
            } catch (Exception e) {
                throw new RuntimeException("Image saving failed", e);
            }
    }

    @Override
    public FileDTO getFile(int bno) throws Exception {
        return fd.getFile(bno);
    }
}
