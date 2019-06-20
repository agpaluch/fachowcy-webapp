package session;

import dao.UserLoginDAO;
import domain.Role;
import domain.UserLogin;
import lombok.Data;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Optional;


@Data
@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {


    @EJB
    private UserLoginDAO userLoginDAO;

    private Role role;
    private String password;
    private String email;
    private UserLogin userLogin;

    @Override
    public boolean validateUser(String email, String password) {

        Optional<UserLogin> userLogin = userLoginDAO.getByLogin(email);

        if (userLogin.isPresent()){
            return userLogin.get().getPassword().equals(password);
        } else {
            return false;
        }
    }



}