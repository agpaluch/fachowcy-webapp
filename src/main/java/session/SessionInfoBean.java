package session;

import dao.*;
import exceptions.NoSuchUserException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {

    @Inject
    @Named("clientsDatabase")
    UserCRUDDao clientsDatabaseDaoBean;

    @Inject
    @Named("professionalsDatabase")
    UserCRUDDao professionalsDatabaseDaoBean;


    private String userType=null;
    private UserLogin userLogin=null;
    private ClientProfile userDetails=null;


    public boolean isAuthorized(String email, String password) {
        if (userType.equals("client")) {
            return clientsDatabaseDaoBean.isAuthorized(email, password);
        } else if (userType.equals("professional")){
            return professionalsDatabaseDaoBean.isAuthorized(email, password);
        }
        return false;
    }


    public void findUserLoginByEmail(String email) throws NoSuchUserException {
          if (userType.equals("client")) {
              userLogin = clientsDatabaseDaoBean.findUserLogin(email);
          } else if (userType.equals("professional")){
              userLogin = professionalsDatabaseDaoBean.findUserLogin(email);
          }
    }


    public void findUserDetailsByEmail(String email) throws NoSuchUserException {
        if (userType.equals("client")) {
            userDetails = clientsDatabaseDaoBean.findUserDetails(email);
        } else if (userType.equals("professional")){
            userDetails = professionalsDatabaseDaoBean.findUserDetails(email);
        }
    }


    public String getUserType() {
        return userType;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public ClientProfile getUserDetails() {
        return userDetails;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserDetails(ClientProfile userDetails) {
        this.userDetails = userDetails;
    }
}