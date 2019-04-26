package dao;


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

    @CheckProfession
    private String profession;

    @NotBlank(message = "Wpisz numer telefonu.")
    @Pattern(regexp = "^\\d{9}$|^(0048)(\\d{9})$|^(\\+48)\\d{9}$", message = "Niepoprawany numer telefonu.")
    private String phoneNumber;


    private String city;

    private String district;

    @NotBlank(message = "Znajdź swoją lokalizację.")
    @Max(value=180L, message = "Niepoprawana lokalizacja.")
    @Min(value=-180L, message = "Niepoprawana lokalizacja.")
    private String longitude;

    @NotEmpty(message = "Znajdź swoją lokalizację.")
    @Max(value=180L, message = "Niepoprawana lokalizacja.")
    @Min(value=-180L, message = "Niepoprawana lokalizacja.")
    private String latitude;

    //login credentials
    @Email(message = "Nieprawidłowy adres email.")
    @NotBlank(message = "Wpisz adres email.")
    private String email;



    public ProfessionalDto(){
        //Non-parametric constructor used in MenuAdd
    }

    public ProfessionalDto(@NotBlank(message = "Wpisz imię.")
                                  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Imię może zawierać tylko litery.")
                                          String name,
                           @NotBlank(message = "Wpisz nazwisko.")
                                  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nazwisko może zawierać tylko litery.")
                                          String surname,
                           @CheckProfession String profession,
                           @NotBlank(message = "Wpisz numer telefonu.")
                                  @Pattern(regexp = "^\\d{9}$|^(0048)(\\d{9})$|^(\\+48)\\d{9}$", message = "Niepoprawany numer telefonu.")
                                          String phoneNumber,
                           String city,
                           String district,
                           @NotBlank(message = "Znajdź swoją lokalizację.")
                                  @Max(value = 180L, message = "Niepoprawana lokalizacja.")
                                  @Min(value = 0L, message = "Niepoprawana lokalizacja.")
                                          String longitude,
                           @NotEmpty(message = "Znajdź swoją lokalizację.")
                                  @Max(value = 180L, message = "Niepoprawana lokalizacja.")
                                  @Min(value = 0L, message = "Niepoprawana lokalizacja.")
                                          String latitude,
                           @Email(message = "Nieprawidłowy adres email.")
                                  @NotBlank(message = "Wpisz adres email.")
                                  String email) {
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
        this.email = email;
    }
}



