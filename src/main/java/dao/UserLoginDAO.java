package dao;

import domain.Role;
import domain.UserDetails;
import domain.UserLogin;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface UserLoginDAO extends RootInterfaceDAO<UserLogin>, Serializable {

    public UserLogin getByLogin(String email);
    public Role getRoleByLogin(String email);
    public UserDetails getDetailsByLogin(String email);

}