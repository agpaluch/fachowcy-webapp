package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Blob;
import java.time.Instant;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "userData")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "profession_id")
    private Professions profession;

    @Builder.Default
    @PastOrPresent
    private Instant signUpDate = Instant.now();

    @Embedded
    private UserDetails userDetails;




    public UserLogin() {
        // used by Hibernate
    }


}

