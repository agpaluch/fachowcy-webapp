package session;

import domain.Role;
import domain.UserLogin;

public interface SessionInfo {

    void setRole(Role role);
    Role getRole();
    UserLogin getUserLogin();

    boolean findUserByEmailAndPassword();
    void setPassword(String password);
    void setEmail(String email);
    void setUserLogin(UserLogin userLogin);

}