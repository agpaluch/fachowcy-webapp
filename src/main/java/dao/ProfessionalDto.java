package dao;


import org.hibernate.validator.constraints.Range;
import validators.CheckCity;
import validators.CheckProfession;

import javax.validation.constraints.*;


public class ProfessionalDto {

    //profile information
    @NotBlank(message = "Wpisz imię.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Imię może zawierać tylko litery.")
    private String name;

    @NotBlank(message = "Wpisz nazwisko.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nazwisko może zawierać tylko litery.")
    private String surname;

    @NotBlank(message = "Wybierz profesję.")
    @CheckProfession
    private String profession;

    @NotBlank(message = "Wpisz numer telefonu.")
    @Pattern(regexp = "(^\\d{9}$)|(^(0048)(\\d{9})$)|(^(\\+48)\\d{9}$)", message = "Niepoprawny numer telefonu.")
    private String phoneNumber;
    //"(^\\d{9}$)|(^(0048)(\\d{9})$)|(^(\\+48)\\d{9}$)"

    @NotBlank(message = "Wybierz miasto.")
    @CheckCity
    private String city;

    //private String district;

    @NotBlank(message = "Znajdź swoją lokalizację.")
    @Range(min=-180L, max=180L, message = "Niepoprawana lokalizacja.")
    private String longitude;

    @NotBlank(message = "Znajdź swoją lokalizację.")
    @Range(min= -180L, max=180L, message = "Niepoprawana lokalizacja.")
    private String latitude;


    //login credentials
    @NotBlank(message = "Wpisz adres email.")
    @Email(message = "Nieprawidłowy adres email.")
    private String email;

    @NotBlank(message = "Wpisz hasło.")
    @Size(min=8, max=20, message = "Hasło musi zawierać od 8 do 20 znaków.")
    private String password;



    public ProfessionalDto(){

    }

    public ProfessionalDto(@NotBlank(message = "Wpisz imię.")
                           @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Imię może zawierać tylko litery.")
                                   String name,
                           @NotBlank(message = "Wpisz nazwisko.")
                           @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nazwisko może zawierać tylko litery.")
                                   String surname,
                           @NotBlank(message = "Wybierz profesję.")
                           @CheckProfession
                                   String profession,
                           @NotBlank(message = "Wpisz numer telefonu.")
                           @Pattern(regexp = "(^\\d{9}$)|(^(0048)(\\d{9})$)|(^(\\+48)\\d{9}$)", message = "Niepoprawny numer telefonu.")
                                   String phoneNumber,
                           @NotBlank(message = "Wybierz miasto.")
                           @CheckCity
                                   String city,
                           @NotBlank(message = "Znajdź swoją lokalizację.")
                           @Range(min= -180L, max=180L, message = "Niepoprawana lokalizacja.")
                                   String longitude,
                           @NotBlank(message = "Znajdź swoją lokalizację.")
                           @Range(min= -180L, max=180L, message = "Niepoprawana lokalizacja.")
                                   String latitude,
                           @NotBlank(message = "Wpisz adres email.")
                           @Email(message = "Nieprawidłowy adres email.")
                                   String email,
                           @NotBlank(message = "Wpisz hasło.")
                           @Size(min=8, max=20, message = "Hasło musi zawierać od 8 do 20 znaków.")
                           String password) {

        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.email = email;
        this.password = password;
    }
}



