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


@Getter
@Setter
@Builder
@AllArgsConstructor
/*@Entity
@Table(name = "userDetails")*/
@Embeddable
public class UserDetails {

    @Column
    @Pattern(regexp = "^[a-zA-Z\\s\\p{L}]+$", message = "Imię może zawierać tylko litery.")
    @NotBlank(message = "Wpisz imię.")
    private String name;

    @Column
    @Pattern(regexp = "^[a-zA-Z\\s\\p{L}]+$", message = "Nazwisko może zawierać tylko litery.")
    @NotBlank(message = "Wpisz nazwisko.")
    private String surname;


    @Column
    @NotNull
    @Range(min=100000000L, max=48999999999L, message = "Niepoprawny numer telefonu.")
    private long phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    @CheckCity
    @NotNull(message = "Wybierz miasto")
    private City city;
    //private CityDistrict district;

    @Column
    @Range(min=-180, max=180, message = "Niepoprawana lokalizacja.")
    @NotNull(message = "Wpisz adres")
    private Double longitude;

    @Column
    @Range(min=-180, max=180, message = "Niepoprawana lokalizacja.")
    @NotNull(message = "Wpisz adres")
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


