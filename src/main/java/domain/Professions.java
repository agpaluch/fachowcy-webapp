package domain;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "professions")
public class Professions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "profession", cascade = {CascadeType.ALL})
    private Set<UserLogin> UserLogin;


    @Column(name="profession")
    private String profession;

    public Professions(){
        //Hibernate
    }

    @Override
    public String toString() {
        return profession;
    }


    public Professions(String profession){
        this.profession = profession;
    }


    public Long getId() {
        return id;
    }

}
