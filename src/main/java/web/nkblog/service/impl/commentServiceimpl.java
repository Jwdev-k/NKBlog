package web.nkblog.service.impl;

import org.springframework.stereotype.Service;
import web.nkblog.domain.commentDTO;
import web.nkblog.domain.impl.commentDAO;
import web.nkblog.service.commentService;

@Service
public class commentServiceimpl implements commentService {

    private static final commentDAO comment = new commentDAO();

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
