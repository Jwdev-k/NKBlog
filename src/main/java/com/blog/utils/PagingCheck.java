package com.blog.utils;

import com.blog.service.impl.boardServiceimpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
public class PagingCheck {

    private boardServiceimpl bbs = new boardServiceimpl();

    private int limit = 10;

    public int lastPage() throws Exception {
        double a = (double) bbs.countBoardList() / getLimit();
        return (int) Math.ceil(a);
    }


    public boolean nextPageCheck(int page) throws Exception {
        return bbs.nextpage(page);
    }
}
