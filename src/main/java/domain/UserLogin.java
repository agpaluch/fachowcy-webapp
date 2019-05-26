package domain;

import java.time.LocalDate;

public interface UserLogin {

    String getEmail();
    String getPassword();
    LocalDate getSignUpDate();


    void setEmail(String email);
    void setPassword(String password);
    void setSignUpDate(LocalDate signUpDate);


}
