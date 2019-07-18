package domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Set;


@Getter
@Setter
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

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "profession_id")
    private Professions profession;


    @Builder.Default
    @PastOrPresent
    //private LocalDate signUpDate = LocalDate.now();
    private Instant signUpDate = Instant.now();

    @Embedded
    private UserDetails userDetails;

/*    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", orphanRemoval = true)
    private Set<Messages> messagesS;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient", orphanRemoval = true)
    private Set<Messages> messagesR;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderC", orphanRemoval = true)
    private Set<Comment> commentS;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipientC", orphanRemoval = true)
    private Set<Comment> commentR;*/

    public UserLogin() {
        // Hibernate
    }


}

