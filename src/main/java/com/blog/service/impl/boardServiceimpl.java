package com.blog.service.impl;

import com.blog.domain.boardDTO;
import com.blog.domain.impl.boardDAO;
import com.blog.service.boardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
public class boardServiceimpl implements boardService {

    private static boardDAO bbs = new boardDAO();

    @Override
    public void addboard(String title, String content, String uid, LocalDate create) throws Exception {
        bbs.addboard(new boardDTO(0, title, uid, create, content, 1));
    }

    @Override
    public ArrayList<boardDTO>boardList(int start) throws Exception {
        if (start != 1) {
            return bbs.boardList(start * 10);
        }
        return bbs.boardList(start - 1);
    }

    @Override
    public boolean nextpage(int start) throws Exception {
        return bbs.nextpage(start).size() > 0;
    }

    @Override
    public void delteboard(int bno) throws Exception {
        bbs.deleteboard(bno);
    }

    @Override
    public void updateboard(boardDTO dto) throws Exception {
        bbs.updateboard(dto);
    }

    @Override
    public int countBoardList() throws Exception {
        return bbs.countBoardList();
    }
}
