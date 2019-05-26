package session;

import domain.UserLogin;
import repository.RepositoryOfUsers;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;


@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {


    private String userType;
    private String password;
    private String email;
    private UserLogin userLogin;

    @Override
    public boolean findUserByEmailAndPassword(){
        RepositoryOfUsers.fillDatabase();
            for (Map.Entry<String, UserLogin> entry : RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().entrySet()) {

                if (entry.getValue().getEmail().equals(email) && entry.getValue().getPassword().equals(password)) {
                    userLogin = entry.getValue();
                    return true;
                }
            }
        return false;
        }



    @Override
    public UserLogin getUserLogin() {
        return userLogin;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }


}