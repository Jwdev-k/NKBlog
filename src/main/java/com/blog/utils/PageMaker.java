package com.blog.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMaker {

    private int totalCount; // 총 게시글 수
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 5; //
    private int page = 0; // 현재페이지
    private int PerPageNum = 10; // 한 페이지 총 게시글 수

    public void setTotalCount(int totalCount) { // 총 게시글 수 설정
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        endPage = (int) (Math.ceil(getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;
        if (startPage <= 0) startPage = 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage != 1;
        next = endPage * getPerPageNum() < totalCount;

    }
}