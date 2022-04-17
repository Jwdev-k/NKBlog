package web.nkblog.service;

import web.nkblog.domain.commentDTO;

import java.util.ArrayList;

public interface commentService {
    commentDTO getComment(commentDTO comment) throws Exception;

    ArrayList<commentDTO> commentList(int bno, int start) throws Exception;

    void addComment(commentDTO cm) throws Exception;

    void setComment(commentDTO comment) throws Exception;

    void deleteComment(int bno) throws Exception;
}
