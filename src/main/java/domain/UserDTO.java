package domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import repository.City;
import repository.TypeOfProfession;
import validators.CheckCity;
import validators.CheckIfNotZero;
import validators.CheckPassword;
import validators.CheckProfession;

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

    @Pattern(regexp = "^[a-zA-Z\\s\\p{L}]+$", message = "Imię może zawierać tylko litery.")
    @NotBlank(message = "Wpisz imię.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z\\s\\p{L}]+$", message = "Nazwisko może zawierać tylko litery.")
    @NotBlank(message = "Wpisz nazwisko.")
    private String surname;

    @CheckProfession
    //@NotNull(message = "Wybierz profesję.")
    private TypeOfProfession profession;


    @Range(min=100000000L, max=48999999999L, message = "Niepoprawny numer telefonu.")
    private Long phoneNumber;

    @CheckCity
    @NotNull(message = "Wybierz miasto")
    private City city;

    @Range(min=-180, max=180, message = "Niepoprawana lokalizacja.")
    @NotNull(message = "Wpisz adres")
    //@CheckIfNotZero(message = "Znajdź swoją lokalizację.")
    private Double longitude;

    @Range(min= -180, max=180, message = "Niepoprawna lokalizacja.")
    @NotNull(message = "Wpisz adres")
    //@CheckIfNotZero(message = "Znajdź swoją lokalizację.")
    private Double latitude;


}
