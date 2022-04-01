package com.blog.service.impl;

import com.blog.domain.commentDTO;
import com.blog.domain.impl.commentDAO;
import com.blog.service.commentService;
import org.springframework.stereotype.Service;

@Service
public class commentServiceimpl implements commentService {

    public static commentDAO comment = new commentDAO();

    @Override
    public void addcomment(commentDTO cm) throws Exception {
        comment.addcomment(cm);
    }
}
