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
    private String email;

    @NotEmpty
    private String password;


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
    private UserDetails userDetails;

    public UserLogin() {
        // Hibernate
    }


}

