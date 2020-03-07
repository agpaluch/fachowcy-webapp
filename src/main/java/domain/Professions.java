package domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class Professions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "profession", cascade = {CascadeType.PERSIST})
    private Set<UserLogin> UserLogin;

    private String profession;

    public Professions(){
        //Constructor used by Hibernate
    }

    public Professions(String profession){
        this.profession = profession;
    }

    @Override
    public String toString() {
        return profession;
    }





}
