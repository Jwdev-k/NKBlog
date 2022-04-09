package com.blog.service;

import com.blog.Enum.EsearchType;
import com.blog.domain.boardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;

public interface boardService {
    ArrayList<boardDTO> boardList(int start) throws Exception;
    void addboard(boardDTO bbs) throws Exception;
    void delteboard(int bno) throws Exception;
    void updateboard(boardDTO dto) throws Exception;
    ArrayList<boardDTO> searchBoard(EsearchType type, String keyword) throws Exception;
    int countBoardList() throws Exception;
    boolean nextPageCheck(int start) throws Exception;
    boardDTO getBoard(int bno) throws Exception;
}
