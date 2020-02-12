package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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

/*    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", orphanRemoval = true)
    private Set<Messages> messagesS;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient", orphanRemoval = true)
    private Set<Messages> messagesR;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderC", orphanRemoval = true)
    private Set<Comment> commentS;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipientC", orphanRemoval = true)
    private Set<Comment> commentR;*/

    public UserLogin() {
        // Constructor used by Hibernate
    }


}

