package com.blog.service;

import com.blog.domain.boardDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public interface boardService {
    void addboard(String title, String content, String uid, LocalDate create);
    ArrayList<boardDTO> getboardlist(int pageNumber) throws Exception;
    boolean nextpage(int pageNumber) throws Exception;
    void delteboard(int bno) throws Exception;
    void updateboard(boardDTO dto) throws Exception;
}
