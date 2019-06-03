package domain;

import lombok.Data;
import repository.City;
import repository.TypeOfProfession;

@Data
public class UserAllData {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String surname;
    private TypeOfProfession profession;
    private long phoneNumber;
    private City city;
    private double longitude;
    private double latitude;


}
