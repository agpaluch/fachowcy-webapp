package session;

import domain.Role;
import domain.UserLogin;

public interface SessionInfo {

    void setRole(Role role);
    Role getRole();
    UserLogin getUserLogin();
    String getEmail();

    boolean validateUser(String email, String password);
    void setPassword(String password);
    void setEmail(String email);
    void setUserLogin(UserLogin userLogin);

}