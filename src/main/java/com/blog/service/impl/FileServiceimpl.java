package com.blog.service.impl;

import com.blog.domain.FileDTO;
import com.blog.domain.impl.FileDAO;
import com.blog.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceimpl implements FileService {

    private static FileDAO fd = new FileDAO();

    @Override
    public void saveFile(FileDTO file) throws Exception {
        fd.saveFile(file);
    }
}
