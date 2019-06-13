package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.Optional;

@Local
public interface UserLoginDAO extends IRootDAO<UserLogin>, Serializable {

    void deleteByLogin(String email);
    Optional<UserLogin> getByLogin(String email);
    Optional<Role> getRoleByLogin(String email);
    Optional<Long> getIDbyLogin(String email);
    Optional<UserDetails> getDetailsByLogin(String email);

}