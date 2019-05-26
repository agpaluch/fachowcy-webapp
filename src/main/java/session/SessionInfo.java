package session;

import domain.ProfessionalLogin;
import domain.UserLogin;

public interface SessionInfo {

    void setUserType(String userType);
    String getUserType();
    ProfessionalLogin getUserLogin();

    boolean findUserByEmailAndPassword();
    void setPassword(String password);
    void setEmail(String email);

}