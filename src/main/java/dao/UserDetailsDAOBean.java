package dao;

import domain.UserLogin;
import repository.City;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class UserDetailsDAOBean implements UserDetailsDAO {

    @PersistenceContext(unitName = "fachmann")
    EntityManager em;

    @Override
    public List<UserLogin> getBySurname(String surname) {
        List<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.userDetails.surname = :val", UserLogin.class)
                .setParameter("val", surname)
                .getResultStream()
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<UserLogin> getByCity(City city) {
        List<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.userDetails.city = :val", UserLogin.class)
                .setParameter("val", city)
                .getResultStream()
                .collect(Collectors.toList());
        return result;
    }
}

