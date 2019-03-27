package dao;

public interface UserLogin {

    String getEmail();
    String getPassword();
    String getSignUpDate();


    void setEmail(String email);
    void setPassword(String password);
    void setSignUpDate(String signUpDate);


}
