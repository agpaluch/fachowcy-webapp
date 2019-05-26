package session;

import domain.ProfessionalLogin;

public interface SessionInfo {

    void setUserType(String userType);
    String getUserType();
    ProfessionalLogin getUserLogin();

    boolean findUserByEmailAndPassword();
    void setPassword(String password);
    void setEmail(String email);
    void setUserLogin(ProfessionalLogin professionalLogin);

}