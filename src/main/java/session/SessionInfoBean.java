package session;

import domain.Role;
import domain.UserLogin;
import lombok.Data;
import repository.RepositoryOfUsers;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;


@Data
@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {


    private Role role;
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



}