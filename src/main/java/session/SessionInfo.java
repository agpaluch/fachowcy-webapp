package session;

import domain.UserLogin;

public interface SessionInfo {

    void setUserType(String userType);
    String getUserType();
    UserLogin getUserLogin();

    boolean findUserByEmailAndPassword();
    void setPassword(String password);
    void setEmail(String email);

}