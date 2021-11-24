package com.blog.service;

import com.blog.domain.boardDTO;

import java.util.ArrayList;

public interface boardService {
    void addboard(String title, String content, String uid);
    ArrayList<boardDTO> getboardlist(int pageNumber) throws Exception;
    boolean nextpage(int pageNumber) throws Exception;
    void delteboard(int bno) throws Exception;
}
