package dao;

import domain.Comment;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface CommentDAO {

    void clearDeletedUser(long id);
    Optional<List<Comment>> getCommentBySender(long id);
    Optional<List<Comment>> getCommentByRecipient(long id);

}
