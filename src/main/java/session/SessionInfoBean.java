package session;
import dao.ClientLogin;
import dao.ProfessionalLogin;
import dao.User;
import dao.UserLogin;
import repository.RepositoryOfUsers;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;


@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {


    private String userType;
    private String password;
    private String email;
    private ClientLogin clientLoginUser;
    private ProfessionalLogin professionalLogin;




    public boolean findUserByEmailAndPassword(){
        if (userType.equals("professional")){

            for (Map.Entry<String, ProfessionalLogin> entry : RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().entrySet()) {

                if (entry.getValue().getEmail().equals(email) && entry.getValue().getPassword().equals(password)) {
                    professionalLogin=entry.getValue();
                    return true;
                }
            }

        }
        else {
            for (Map.Entry<String, ClientLogin> entry : RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().entrySet()) {

                if (entry.getValue().getEmail().equals(email) && entry.getValue().getPassword().equals(password)) {
                    clientLoginUser = entry.getValue();
                    return true;
                }
            }
        }
        return false;
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



    public ProfessionalLogin getProfessionalLogin() {
        return professionalLogin;
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