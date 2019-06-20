package dao;

import domain.Messages;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Singleton
public class MessagesDAOBean implements MessagesDAO {

    // and some additional methods

    @Override
    public List<Messages> getAll() {
        return null;
    }

    @Override
    public void save(Messages domain) {

    }

    @Override
    public Optional<Messages> get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
