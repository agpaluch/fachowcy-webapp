package dao;

import domain.Messages;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class MessagesDAOBean implements MessagesDAO {

    // and some additional methods

    @Override
    public List<Messages> getAll() {
        return null;
    }

    @Override
    public Messages save(Messages domain) {
        return null;
    }

    @Override
    public Messages get(Long id) {
        return null;
    }

    @Override
    public void update(Messages domain) {

    }

    @Override
    public void delete(Messages domain) {

    }

    @Override
    public EntityManager startTransaction() {
        return null;
    }

    @Override
    public void commit(EntityManager entityManager) {

    }
}
