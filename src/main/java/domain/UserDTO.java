package domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import repository.City;
import validators.CheckCity;
import validators.CheckPassword;
import validators.CheckRole;

import javax.validation.constraints.*;

@Data
@CheckPassword(first = "password", second = "confirmPassword")
@Builder
public class UserDTO {

    @Email(message = "Nieprawidłowy adres email.")
    @NotBlank(message = "Wpisz adres email.")
    private String email;

    @NotBlank(message = "Wpisz hasło")
    private String password;

    @NotBlank(message = "Potwierdź hasło")
    private String confirmPassword;


    @CheckRole
    private Role role;


    @Pattern(regexp = "^[a-zA-Z\\s\\p{L}]+$", message = "Imię może zawierać tylko litery.")
    @NotBlank(message = "Wpisz imię.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z\\s\\p{L}]+$", message = "Nazwisko może zawierać tylko litery.")
    @NotBlank(message = "Wpisz nazwisko.")
    private String surname;

    //TODO Add check for profession
    //@CheckProfession
    //@NotNull(message = "Wybierz profesję.")
    private String profession;


    @Range(min = 100000000L, max = 48999999999L, message = "Niepoprawny numer telefonu.")
    private Long phoneNumber;

    @Min(0)
    @Builder.Default
    private Integer numberOfLikes = 0;

    @CheckCity
    @NotNull(message = "Wybierz miasto")
    private City city;

    @Range(min = -180, max = 180, message = "Niepoprawana lokalizacja.")
    @NotNull(message = "Wpisz adres")
    private Double longitude;

    @Range(min = -180, max = 180, message = "Niepoprawna lokalizacja.")
    @NotNull(message = "Wpisz adres")
    private Double latitude;


}
