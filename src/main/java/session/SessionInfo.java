package session;

import dao.ClientProfile;
import dao.Role;
import dao.UserLogin;
import exceptions.NoSuchUserException;

public interface SessionInfo {

    void setUserType(Role userType);
    Role getUserType();
    UserLogin getUserLogin();
    ClientProfile getUserDetails();



    boolean isAuthorized(String email, String password);
    void findUserLoginByEmail(String email) throws NoSuchUserException;
    void findUserDetailsByEmail(String email) throws NoSuchUserException;

}