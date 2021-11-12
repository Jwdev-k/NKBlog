package com.blog.service.impl;

import com.blog.domain.boardDTO;
import com.blog.domain.impl.boardDAO;
import com.blog.service.boardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class boardServiceimpl implements boardService {

    public static boardDAO bbs = new boardDAO();

    @Override
    public void addboard(String title, String content, String uid) {
        try {
            LocalDateTime now = LocalDateTime.now();
            bbs.addboard(new boardDTO(0, title, uid, now, content, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getboardlist() {

    }
}
