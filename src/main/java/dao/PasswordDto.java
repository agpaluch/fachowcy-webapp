package dao;

import validators.CheckPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@CheckPassword(first = "password", second = "confirmPassword")
public class PasswordDto {

    //@Size(min=8, max=20, message = "Hasło musi zawierać pomiędzy 8 a 20 znaków.")
    //@NotEmpty(message = "Podaj hasło.")
    private String password;

    //@NotEmpty(message = "Potwierdź hasło.")
    private String confirmPassword;

    public PasswordDto(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
