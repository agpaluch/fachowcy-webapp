package dao;

import domain.Comment;
import domain.Messages;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface CommentDAO {

    public void clearDeletedUser(long id);
    public Optional<List<Comment>> getBySender(long id);
    public Optional<List<Comment>> getByRecipient(long id);

}
