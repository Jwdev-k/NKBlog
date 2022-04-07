package com.blog.service;

import com.blog.domain.boardDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public interface boardService {
    void addboard(String title, String content, String uid, LocalDate create) throws Exception;
    ArrayList<boardDTO> boardList(int start) throws Exception;
    boolean nextpage(int start) throws Exception;
    void delteboard(int bno) throws Exception;
    void updateboard(boardDTO dto) throws Exception;
    int countBoardList() throws Exception;
}
