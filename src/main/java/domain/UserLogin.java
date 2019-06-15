package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "userData")
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

