package domain;


import java.time.LocalDate;


public class ProfessionalLogin {

    private String email;
    private String password;
    private LocalDate signUpDate;

    public ProfessionalLogin(String email, String password) {

        this.email = email;
        this.password = password;
        this.signUpDate =  LocalDate.now();

    }

    public ProfessionalLogin() {
        // Hibernate
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }


    public String toString(){
        return("Login: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" + "Sign-up-date: " + getSignUpDate()+ "\n" );
    }


}

