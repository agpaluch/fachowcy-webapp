package dao;

import domain.UserDetails;
import repository.City;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    public Optional<UserDetails> get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public EntityManager startTransaction() {
        return null;
    }

    @Override
    public void commit(EntityManager entityManager) {

    }
}
