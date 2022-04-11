package com.blog.service;

import com.blog.domain.FileDTO;

public interface FileService {
    void saveFile(FileDTO file) throws Exception;
}
