package domain;

import lombok.*;
import repository.City;
import repository.TypeOfProfession;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Data
@Builder
@AllArgsConstructor
/*@Entity
@Table(name = "userDetails")*/
@Embeddable
public class UserDetails {

/*    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    UserLogin userLogin;

    @Id
    @Column(name = "id")
    private Long id;*/

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String surname;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfProfession profession;

    @Column
    @NotNull
    private long phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private City city;
    //private CityDistrict district;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Builder.Default
    @Column
    private int numberOfLikes = 0;



   public UserDetails(){
        // Hibernate
    }


}


