package domain;

import lombok.*;
import repository.TypeOfProfession;
import validators.CheckPassword;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "userData")
@CheckPassword(first = "password", second = "confirmPassword")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    @NotEmpty
    @Email(message = "Nieprawidłowy adres email.")
    @NotBlank(message = "Wpisz adres email.")
    private String email;

    @NotEmpty
    @NotBlank(message = "Wpisz hasło")
    private String password;

    @Transient
    @NotBlank(message = "Potwierdź hasło")
    private String confirmPassword;


    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "profession_id")
    private Professions profession;


    @Builder.Default
    @PastOrPresent
    //private LocalDate signUpDate = LocalDate.now();
    private Instant signUpDate = Instant.now();

    @Embedded
    @Valid
    private UserDetails userDetails;

    public UserLogin() {
        // Hibernate
    }


    public String toString() {
        return
                "id: " + id +
                ", email: '" + email + '\'' +
                ", password: '" + password + '\'' +
                ", role: " + role.toString() +
                ", profession: " + profession.toString() +
                ", signUpDate: " + signUpDate;
    }
}

