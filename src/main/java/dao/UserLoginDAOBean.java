package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.List;


@Stateful
public class UserLoginDAOBean implements UserLoginDAO {

    @Override
    public UserLogin getByLogin(String email) {
        return null;
    }

    @Override
    public UserDetails getDetailsByLogin(String email) {
        return null;
    }

    @Override
    public Role getRoleByLogin(String email) {
        return null;
    }

    @Override
    public List<UserLogin> getAll() {
        return null;
    }

    @Override
    public void save(UserLogin domain) {

    }

    @Override
    public UserLogin get(Serializable id) {
        return null;
    }

    @Override
    public void update(UserLogin domain) {

    }

    @Override
    public void delete(UserLogin domain) {

    }
}
