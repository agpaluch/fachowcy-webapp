package dao;

import domain.Messages;
import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface MessagesDAO extends IRootDAO<Messages> {

    Optional<List<Messages>> getBySender(long id);
    Optional<List<Messages>> getByRecipient(long id);
    void setToRead(long id);
    void clearDeletedUser(long id);
}
