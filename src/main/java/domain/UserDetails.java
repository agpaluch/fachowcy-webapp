package domain;

import lombok.*;
import repository.City;
import repository.TypeOfProfession;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;


@Getter
@Setter
@Builder
@AllArgsConstructor
/*@Entity
@Table(name = "userDetails")*/
@Embeddable
public class UserDetails {

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String surname;


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





    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#");

        return "name: '" + name + '\'' +
                ", surname: '" + surname + '\'' +
                ", phoneNumber: " + decimalFormat.format(phoneNumber) +
                ", city: " + city.toString() +
                ", longitude: " + longitude +
                ", latitude: " + latitude +
                ", numberOfLikes: " + numberOfLikes;
    }
}


