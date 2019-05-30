package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "userLogin")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String email;

    @OneToOne(mappedBy = "userLogin", cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetailsID")
    private UserDetails userDetails;

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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String toString(){
        return("Login: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n" + "Sign-up-date: " + getSignUpDate()+ "\n" );
    }


}

