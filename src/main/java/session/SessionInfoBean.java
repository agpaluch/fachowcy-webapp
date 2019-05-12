package session;
import dao.ClientLogin;
import dao.ProfessionalLogin;
import dao.User;
import dao.UserLogin;
import repository.RepositoryOfUsers;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;


@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {


    private String userType;
    private String password;
    private String email;

    public UserLogin findUserByEmailAndPassword(String password, String email){
        if (userType.equals("professional")){


            RepositoryOfUsers
                    .getProfessionalsDatabaseDaoBean()
                    .getLogin()
                    .entrySet()
                    .stream()
            .filter(e -> e.getKey().equals(email))
            .filter()


            ;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private ClientLogin clientLoginUser;
    private ProfessionalLogin professionalLogin;

    public ProfessionalLogin getProfessionalLogin() {
        return professionalLogin;
    }

    public void setProfessionalLogin(ProfessionalLogin professionalLogin) {
        this.professionalLogin = professionalLogin;
    }




    public void setClientLogin(ClientLogin clientLoginUser) {
        this.clientLoginUser=clientLoginUser;
    }
    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getUserType() {
        return this.userType;
    }

    public ClientLogin getClientLoginUser() {
        return clientLoginUser;
    }
}