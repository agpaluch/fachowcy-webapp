package domain;

import java.time.LocalDate;

public class ClientLogin implements UserLogin {

    //login credentials
    private String email;
    private String password;
    private LocalDate signUpDate;



    public ClientLogin(String email, String password) {


        this.email = email;
        this.password = password;
        this.signUpDate = LocalDate.now();


    }

    public ClientLogin() {
        // Non-parametric constructor used in MenuAdd
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDate getSignUpDate() {
        return signUpDate;
    }



    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }


    public String toString(){
        return("Email: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" + "Sign-up-date: " + getSignUpDate() + "\n" );
    }


}