package domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import repository.City;
import repository.TypeOfProfession;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Data
@Entity
@Table(name = "userDetails")
public class UserDetails {

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    UserLogin userLogin;

    @Id
    @Column(name = "id")
    private Long id;

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
    private double longitude;

    @Column
    private double latitude;

    @Column
    private int numberOfLikes = 0;

    @Column
    private String comments = "";

    public UserDetails(){
        // Hibernate
    }

    public UserDetails(String name, String surname, TypeOfProfession profession,
                               long phoneNumber, City city,
                               //CityDistrict district,
                               double longitude, double latitude) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.city = city;
        //this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
    }


}


