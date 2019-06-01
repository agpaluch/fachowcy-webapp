package dao;

import domain.UserDetails;
import repository.City;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
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
}
