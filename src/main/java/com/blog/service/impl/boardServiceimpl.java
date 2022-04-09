package com.blog.service.impl;

import com.blog.Enum.EsearchType;
import com.blog.config.AsyncService;
import com.blog.domain.boardDTO;
import com.blog.domain.impl.boardDAO;
import com.blog.service.boardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@Service
public class boardServiceimpl implements boardService {

    private static boardDAO bbs = new boardDAO();
    @Autowired
    private AsyncService async;

    @Override
    public ArrayList<boardDTO>boardList(int start) throws Exception {
        if (start != 1) {
            return bbs.boardList(start * 10);
        }
        return bbs.boardList(start - 1);
    }

    @Override
    public void addboard(boardDTO board) throws Exception {
        bbs.addboard(board);
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
    public ArrayList<boardDTO> searchBoard(EsearchType type, String keyword) throws Exception {
        log.debug("검색 타입: " + type + "키워드: " + keyword);
        if (EsearchType.content.equals(type)) {
            return bbs.searchBoardContent("%" + keyword + "%");
        } else if (EsearchType.uid.equals(type)) {
            return bbs.searchBoardUid("%" + keyword + "%");
        } else {
            return bbs.searchBoardTitle("%" + keyword + "%");
        }
    }

    @Override
    public int countBoardList() throws Exception {
        return bbs.countBoardList();
    }

    @Override
    public boolean nextPageCheck(int start) throws Exception {
        return bbs.nextPageCheck((start * 10) + 10);
    }

    @Override
    public boardDTO getBoard(int bno) throws Exception {
        return bbs.getBoard(bno);
    }
}
