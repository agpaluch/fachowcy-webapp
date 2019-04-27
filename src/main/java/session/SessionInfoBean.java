package session;
import dao.ClientLogin;
import dao.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;


@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {

    private User user;
    private String userType;
    private ClientLogin clientLoginUser;
    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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