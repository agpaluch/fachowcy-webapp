package dao;

import org.hibernate.validator.constraints.Range;
import validators.CheckPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@CheckPassword(first = "password", second = "confirmPassword")
public class PasswordDto {

    private String password;
    private String confirmPassword;

    public PasswordDto(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
