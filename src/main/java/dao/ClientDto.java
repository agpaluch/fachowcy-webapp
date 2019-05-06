package dao;


import org.hibernate.validator.constraints.Range;
import validators.CheckCity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ClientDto {

    //profile information
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Imię może zawierać tylko litery.")
    @NotBlank(message = "Wpisz imię.")
    protected String name;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nazwisko może zawierać tylko litery.")
    @NotBlank(message = "Wpisz nazwisko.")
    protected String surname;


    @Pattern(regexp = "(^\\d{9}$)|(^(0048)(\\d{9})$)|(^(\\+48)\\d{9}$)", message = "Niepoprawny numer telefonu.")
    @NotBlank(message = "Wpisz numer telefonu.")
    protected String phoneNumber;
    //"(^\\d{9}$)|(^(0048)(\\d{9})$)|(^(\\+48)\\d{9}$)"

    @CheckCity
    @NotBlank(message = "Wybierz miasto.")
    private String city;

    //private String district;

    @Range(min=-180L, max=180L, message = "Niepoprawana lokalizacja.")
    @NotBlank(message = "Znajdź swoją lokalizację.")
    private String longitude;

    @Range(min= -180L, max=180L, message = "Niepoprawana lokalizacja.")
    @NotBlank(message = "Znajdź swoją lokalizację.")
    private String latitude;


    //login credentials
    @Email(message = "Nieprawidłowy adres email.")
    @NotBlank(message = "Wpisz adres email.")
    private String email;

    @Size(min=8, max=20, message = "Hasło musi zawierać od 8 do 20 znaków.")
    @NotBlank(message = "Wpisz hasło.")
    private String password;



    public ClientDto(){

    }

    public ClientDto(String name, String surname, String phoneNumber, String city, String longitude,
                           String latitude, String email, String password) {

        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.email = email;
        this.password = password;
    }
}



