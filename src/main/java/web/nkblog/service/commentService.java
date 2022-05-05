package web.nkblog.service;

import web.nkblog.domain.CommentDTO;

import java.util.ArrayList;

public interface CommentService {
    CommentDTO getComment(CommentDTO comment) throws Exception;

    ArrayList<CommentDTO> commentList(int bno, int start) throws Exception;

    void addComment(CommentDTO cm) throws Exception;

    void setComment(CommentDTO comment) throws Exception;

    void deleteComment(int bno) throws Exception;
}
