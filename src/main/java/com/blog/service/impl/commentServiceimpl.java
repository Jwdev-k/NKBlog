package com.blog.service.impl;

import com.blog.domain.commentDTO;
import com.blog.domain.impl.commentDAO;
import com.blog.service.commentService;
import org.springframework.stereotype.Service;

@Service
public class commentServiceimpl implements commentService {

    public static commentDAO comment = new commentDAO();

    @Override
    public commentDTO getComment(commentDTO cm) throws Exception {
        return comment.getComment(cm);
    }

    @Override
    public void addComment(commentDTO cm) throws Exception {
        comment.addComment(cm);
    }

    @Override
    public void setComment(commentDTO cm) throws Exception {
        comment.setComment(cm);
    }

    @Override
    public void deleteComment(int bno) throws Exception {
        comment.deleteComment(bno);
    }
}
