package session;
import dao.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;


@SessionScoped
public class SessionInfoBean implements SessionInfo, Serializable {

    private User user;
    private String userType;

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getUserType() {
        return this.userType;
    }
}