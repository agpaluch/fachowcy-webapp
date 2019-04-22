package session;

import dao.User;

public interface SessionInfo {

    public User getUser();
    public void setUser(User user);

    public void setUserType(String userType);
    public String getUserType();

}