package dao;

import domain.UserDetails;
import repository.City;

import javax.ejb.Singleton;
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
    public UserDetails getByCity(City city) {
        return null;
    }

    @Override
    public UserDetails getByProfession(String profession) {
        return null;
    }

}
