package dao;

import domain.Comment;
import domain.Messages;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Singleton
public class CommentDAOBean implements CommentDAO {

    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Override
    public void clearDeletedUser(long id) {
        Optional<List<Comment>> senderComments = getBySender(id);
        Optional<List<Comment>> recipientComments = getByRecipient(id);

        for(Comment c : senderComments.get()) {
            c.setSenderC(null);
            em.merge(c);
        }

        for(Comment c : recipientComments.get()) {
            c.setRecipientC(null);
            em.merge(c);
        }
    }

    @Override
    public Optional<List<Comment>> getBySender(long id) {
        return Optional.of(em.createQuery("SELECT com FROM Comment com WHERE com.senderC.id = :val", Comment.class)
                .setParameter("val", id)
                .getResultList());
    }

    @Override
    public Optional<List<Comment>> getByRecipient(long id) {
        return Optional.of(em.createQuery("SELECT com FROM Comment com WHERE com.recipientC.id = :val", Comment.class)
                .setParameter("val", id)
                .getResultList());
    }
}
