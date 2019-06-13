package dao;

import domain.UserDetails;
import repository.City;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

@Singleton
public class UserDetailsDAOBean implements UserDetailsDAO {

    @Override
    public UserDetails getByName(String name) {
        return null;
    }

    @Override
    public UserDetails getbyCity(City city) {
        return null;
    }

    @Override
    public UserDetails getByProfession(String profession) {
        return null;
    }

    @Override
    public List<UserDetails> getAll() {
        return null;
    }

    @Override
    public void save(UserDetails domain) {

    }

    @Override
    public UserDetails get(Long id) {
        return null;
    }

    @Override
    public void update(UserDetails domain) {

    }

    @Override
    public void delete(UserDetails domain) {

    }

    @Override
    public EntityManager startTransaction() {
        return null;
    }

    @Override
    public void commit(EntityManager entityManager) {

    }
}
