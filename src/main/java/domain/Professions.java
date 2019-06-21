package domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import repository.TypeOfProfession;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "professions")
public class Professions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL)
    private Set<UserLogin> UserLogin;


    @Column(name="profession")
    @Enumerated(EnumType.STRING)
    private TypeOfProfession profession;

    public Professions(){
        //Hibernate
    }


    public Professions(TypeOfProfession profession){
        this.profession = profession;
    }


    public String toString() {
        return profession.toString();
    }


}
