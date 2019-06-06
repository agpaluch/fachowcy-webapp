package dto;


import validators.CheckProfession;

import javax.validation.constraints.NotBlank;


public class ProfessionalDto extends ClientDto{

    //@CheckProfession
    @NotBlank(message = "Wybierz profesję.")
    private String profession;

    public ProfessionalDto(){

    }

    public ProfessionalDto(String name, String surname, String profession, String phoneNumber, String city,
                           String longitude, String latitude, String email, String password) {

        super(name, surname, phoneNumber, city,
                longitude, latitude, email, password);
        this.profession = profession;
    }


/*    public ProfessionalDto(@NotBlank(message = "Wpisz imię.")
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
    }*/
}



