package web.nkblog.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@Setter
@Slf4j
public class PageUtil {
    private int totalCount; // 총 게시글 수
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 5; // 한페이지당 페이징번호 갯수
    private int page = 1; // 현재페이지
    private int perPageNum = 10; // 한 페이지 총 게시글 수
    private int lastEndPage;
    private ArrayList<Integer> pageNumList = new ArrayList<>(); //페이징 버튼 리스트

    public void setTotalCount(int totalCount){ // 총 게시글 수 설정
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(getPage() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;
        if (startPage <= 0) {
            startPage = 1;
        }
        lastEndPage = (int) (Math.ceil((double) getTotalCount() / getPerPageNum())) - 1;
        if (endPage >= lastEndPage) {
            endPage = lastEndPage;
        }
        next = (endPage + 1) * getPerPageNum() < totalCount;
        prev = startPage != 1;
        pageNumList.clear();
        for (int i = endPage; i >= endPage - displayPageNum; i--) {
            if (i > 0) {
                pageNumList.add(i);
            }
        }
    }
}