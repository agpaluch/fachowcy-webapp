package dao;

import domain.Messages;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.util.List;

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
    public Messages get(Long id) {
        return null;
    }

    @Override
    public void update(Messages domain) {

    }

    @Override
    public void delete(Messages domain) {

    }
}
