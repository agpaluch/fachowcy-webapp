package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
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
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Enumerated
    @NotNull
    private Role role;

    @Builder.Default
    @PastOrPresent
    private LocalDate signUpDate = LocalDate.now();


   public UserLogin() {
        // Hibernate
    }


}

