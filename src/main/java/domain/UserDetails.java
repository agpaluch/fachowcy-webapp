package domain;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import repository.City;
import repository.TypeOfProfession;
import validators.CheckCity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.DecimalFormat;


@Data
@Builder
@AllArgsConstructor
/*@Entity
@Table(name = "userDetails")*/
@Embeddable
public class UserDetails {

    @Column
    private String name;

    @Column
    private String surname;

    @Column
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


