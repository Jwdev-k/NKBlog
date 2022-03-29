package com.blog.service.impl;

import com.blog.domain.boardDTO;
import com.blog.domain.impl.boardDAO;
import com.blog.service.boardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class boardServiceimpl implements boardService {

    public static boardDAO bbs = new boardDAO();

    @Override
    public void addboard(String title, String content, String uid, LocalDate create) {
        try {
            bbs.addboard(new boardDTO(0, title, uid, create, content, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<boardDTO> getboardlist(int pageNumber) throws Exception {
        return bbs.boardList(pageNumber);
    }

    @Override
    public boolean nextpage(int pageNumber) throws Exception {
        return bbs.nextpage(pageNumber).size() > (pageNumber - 1) * 20;
    }

    @Override
    public void delteboard(int bno) throws Exception {
        bbs.deleteboard(bno);
    }

    @Override
    public void updateboard(boardDTO dto) throws Exception {
        bbs.updateboard(dto);
    }
}
