package dao;

import domain.Role;
import domain.UserLogin;
import repository.City;
import repository.TypeOfProfession;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class UserDetailsDAOBean extends TransactionsUtil implements UserDetailsDAO {

    @Override
    public List<UserLogin> getBySurname(String surname) {
        EntityManager em = startTransaction();
        List<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.userDetails.surname = :val", UserLogin.class)
                .setParameter("val", surname)
                .getResultStream()
                .collect(Collectors.toList());
        commit(em);
        return result;
    }

    @Override
    public List<UserLogin> getByCity(City city) {
        EntityManager em = startTransaction();
        List<UserLogin> result = em.createQuery("SELECT ul FROM UserLogin ul WHERE ul.userDetails.city = :val", UserLogin.class)
                .setParameter("val", city)
                .getResultStream()
                .collect(Collectors.toList());
        commit(em);
        return result;
    }




}

