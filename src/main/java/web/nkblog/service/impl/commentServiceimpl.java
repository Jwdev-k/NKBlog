package web.nkblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.nkblog.domain.commentDTO;
import web.nkblog.domain.impl.commentDAO;
import web.nkblog.service.commentService;

import java.util.ArrayList;

@Service
public class commentServiceimpl implements commentService {

    @Autowired
    private commentDAO comment;

    @Override
    public commentDTO getComment(commentDTO cm) throws Exception {
        return comment.getComment(cm);
    }

    @Override
    public ArrayList<commentDTO> commentList(int bno, int start) throws Exception {
        if (start != 1) {
          return comment.commentList(bno,start);
        } else {
            return comment.commentList(bno, 0);
        }
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
