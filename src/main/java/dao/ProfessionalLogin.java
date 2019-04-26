package dao;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class ProfessionalLogin implements UserLogin {

    //login credentials
    @Email(message = "Nieprawidłowy adres email.")
    @NotBlank(message = "Wpisz adres email.")
    private String email;

    @Size(min=8, max=20, message = "Hasło musi zawierać pomiędzy 8 a 20 znaków.")
    @NotBlank(message = "Podaj hasło.")
    private String password;
    private LocalDate signUpDate;


    public ProfessionalLogin(String email, String password) {

        this.email = email;
        this.password = password;
        this.signUpDate =  LocalDate.now();

    }

    public ProfessionalLogin() {
        //Non-parametric constructor used in MenuAdd
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




    /*    @Override
    public void setPassword() {

    }

    @Override
    public void setSignUpDate() {

    }*/

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
        return("Login: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" + "Sign-up-date: " + getSignUpDate()+ "\n" );
    }


}

