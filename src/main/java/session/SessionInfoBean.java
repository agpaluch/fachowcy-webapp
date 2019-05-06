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


    private Role userType=null;
    private UserLogin userLogin=null;
    private ClientProfile userDetails=null;


    public boolean isAuthorized(String email, String password) {
        if (userType==Role.CLIENT) {
            return clientsDatabaseDaoBean.isAuthorized(email, password);
        } else if (userType==Role.PROFESSIONAL){
            return professionalsDatabaseDaoBean.isAuthorized(email, password);
        }
        return false;
    }


    public void findUserLoginByEmail(String email) throws NoSuchUserException {
          if (userType==Role.CLIENT) {
              userLogin = clientsDatabaseDaoBean.findUserLogin(email);
          } else if (userType==Role.PROFESSIONAL){
              userLogin = professionalsDatabaseDaoBean.findUserLogin(email);
          }
    }


    public void findUserDetailsByEmail(String email) throws NoSuchUserException {
        if (userType==Role.CLIENT) {
            userDetails = clientsDatabaseDaoBean.findUserDetails(email);
        } else if (userType==Role.PROFESSIONAL){
            userDetails = professionalsDatabaseDaoBean.findUserDetails(email);
        }
    }

    public void clean() {
        userType=null;
        userLogin=null;
        userDetails=null;
    }


    public Role getUserType() {
        return userType;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public ClientProfile getUserDetails() {
        return userDetails;
    }

    public void setUserType(Role userType) {
        this.userType = userType;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserDetails(ClientProfile userDetails) {
        this.userDetails = userDetails;
    }
}