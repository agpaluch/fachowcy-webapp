package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import repository.City;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Builder
@AllArgsConstructor
/*@Entity
@Table(name = "userDetails")*/
@Embeddable
public class UserDetails {

    private String name;
    private String surname;
    private long phoneNumber;

    @Enumerated(EnumType.STRING)
    private City city;
    //private CityDistrict district;

    private Double longitude;
    private Double latitude;

    @Builder.Default
    private int numberOfLikes = 0;

   public UserDetails(){
        // Hibernate
    }



}


