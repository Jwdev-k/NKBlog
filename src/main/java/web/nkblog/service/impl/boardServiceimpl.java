package web.nkblog.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.nkblog.enums.EsearchType;
import web.nkblog.config.AsyncService;
import web.nkblog.domain.boardDTO;
import web.nkblog.domain.impl.boardDAO;
import web.nkblog.service.boardService;

import java.util.ArrayList;

@Slf4j
@Service
public class boardServiceimpl implements boardService {

    @Autowired
    private boardDAO bbs;
    @Autowired
    private AsyncService async;

    @Override
    public ArrayList<boardDTO> boardList(int start) throws Exception {
        if (start != 1) {
            return bbs.boardList(start * 10);
        }
        return bbs.boardList(0);
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
        return switch (type) {
            case content -> bbs.searchBoardContent("%" + keyword + "%");
            case uid -> bbs.searchBoardUid("%" + keyword + "%");
            case title -> bbs.searchBoardTitle("%" + keyword + "%");
        };
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

    @Override
    public int lastBno() throws Exception {
        return bbs.lastBno();
    }
}
