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
    private int displayPageNum = 5; // 페이징번호 갯수
    private int page = 1; // 현재페이지
    private int PerPageNum = 10; // 한 페이지 총 게시글 수
    private ArrayList<Integer> pageNumList = new ArrayList<>();

    public void setTotalCount(int totalCount) { // 총 게시글 수 설정
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(getPage() / (double) displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum) + 1;
        if (startPage <= 0) {
            startPage = 1;
        }
        int tempEndPage = (int) (Math.ceil((double) getTotalCount() / getPerPageNum())) - 1;
        if (endPage >= tempEndPage) {
            endPage = tempEndPage;
        }
        next = (endPage + 1) * getPerPageNum() < totalCount;
        prev = startPage != 1;
        pageNumList.clear();
        for (int i = endPage; i >= startPage; i--) {
            pageNumList.add(i);
        }
    }
}