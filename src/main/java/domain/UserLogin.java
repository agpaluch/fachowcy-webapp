package domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "userLogin")
public class UserLogin {

    @OneToOne(mappedBy = "userLogin", cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetailsID")
    private UserDetails userDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    @NotEmpty
    private String password;

    @Column
    @NotNull

    @Enumerated
    private Role role;


    @Column
    @PastOrPresent
    private LocalDate signUpDate;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
        this.signUpDate = LocalDate.now();
    }

    public UserLogin() {
        // Hibernate
    }


}

