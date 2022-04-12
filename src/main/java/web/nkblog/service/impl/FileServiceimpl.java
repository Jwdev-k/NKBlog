package web.nkblog.service.impl;

import org.springframework.stereotype.Service;
import web.nkblog.domain.FileDTO;
import web.nkblog.domain.impl.FileDAO;
import web.nkblog.service.FileService;

@Service
public class FileServiceimpl implements FileService {

    private static FileDAO fd = new FileDAO();

    @Override
    public void saveFile(FileDTO file) throws Exception {
        fd.saveFile(file);
    }
}
