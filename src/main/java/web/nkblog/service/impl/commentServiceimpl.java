package web.nkblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.nkblog.domain.CommentDTO;
import web.nkblog.domain.impl.commentDAO;
import web.nkblog.service.CommentService;

import java.util.ArrayList;

@Service
public class CommentServiceimpl implements CommentService {

    @Autowired
    private commentDAO comment;

    @Override
    public CommentDTO getComment(CommentDTO cm) throws Exception {
        return comment.getComment(cm);
    }

    @Override
    public ArrayList<CommentDTO> commentList(int bno, int start) throws Exception {
        if (start != 1) {
          return comment.commentList(bno,start);
        } else {
            return comment.commentList(bno, 0);
        }
    }

    @Override
    public void addComment(CommentDTO cm) throws Exception {
        comment.addComment(cm);
    }

    @Override
    public void setComment(CommentDTO cm) throws Exception {
        comment.setComment(cm);
    }

    @Override
    public void deleteComment(int bno) throws Exception {
        comment.deleteComment(bno);
    }
}
