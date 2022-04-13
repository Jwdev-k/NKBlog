package web.nkblog.service;

import web.nkblog.domain.commentDTO;

public interface commentService {
    commentDTO getComment(commentDTO comment) throws Exception;

    void addComment(commentDTO cm) throws Exception;

    void setComment(commentDTO comment) throws Exception;

    void deleteComment(int bno) throws Exception;
}
