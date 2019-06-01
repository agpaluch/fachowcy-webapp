package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.Optional;

@Local
public interface UserLoginDAO extends IRootDAO<UserLogin>, Serializable {

    public Optional<UserLogin> getByLogin(String email);
    public Role getRoleByLogin(String email);
    public UserDetails getDetailsByLogin(String email);

}