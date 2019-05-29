package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table
public class UserLogin {

    @Id
    private String email;

    @Column
    @NotEmpty
    private String password;

    @NotNull
    @PastOrPresent
    private LocalDate signUpDate;

    @NotNull
    private Role role;


    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
        this.signUpDate =  LocalDate.now();
    }

    public UserLogin() {
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

