package dao;

import domain.Messages;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class MessagesDAOBean implements MessagesDAO {

    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Override
    public List<Messages> getAll() {
        return em.createQuery("SELECT msg FROM Messages msg", Messages.class).getResultList();
    }

    @Override
    public void save(Messages message) {
        if (get(message.getRecipient().getId()).isPresent() && get(message.getSender().getId()).isPresent() && message.getMessage().length() > 0) {
            em.persist(message);
        } else {
            throw new NoSuchElementException("Bad message");
        }
    }

    @Override
    public Optional<Messages> get(Long id) {
        return Optional.of(em.find(Messages.class, id));
    }

    @Override
    public void delete(Long id) {
        get(id).ifPresent((msg) -> {
            em.remove(em.merge(msg));
        });
    }

    @Override
    public Optional<List<Messages>> getBySender(long id) {
        return Optional.of(em.createQuery("SELECT msg FROM Messages msg WHERE msg.sender.id = :val", Messages.class)
                .setParameter("val", id)
                .getResultList());
    }

    @Override
    public Optional<List<Messages>> getByRecipient(long id) {
        return Optional.of(em.createQuery("SELECT msg FROM Messages msg WHERE msg.recipient.id = :val", Messages.class)
                .setParameter("val", id)
                .getResultList());
    }

    @Override
    public void setToRead(long id) {
        Optional<Messages> message = get(id);
        message.ifPresent(msg ->
        {
            msg.setWasRead(true);
            em.merge(msg);
        });
    }

    @Override
    public void clearDeletedUser(long id) {
        Optional<List<Messages>> senderMessages = getBySender(id);
        Optional<List<Messages>> recipientMessages = getByRecipient(id);

        for(Messages m : senderMessages.get()) {
            m.setSender(null);
            em.merge(m);
        }

        for(Messages m : recipientMessages.get()) {
            m.setRecipient(null);
            em.merge(m);
        }

    }
}
