package dao;


import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;
import repository.RepositoryOfUsers;

import javax.ejb.Stateful;
import java.util.Map;

@Stateful
public class ClientsDatabaseDaoBean implements UserCRUDDao {

    private Map<String, ClientLogin> clientLogin;
    private Map<String, ClientDetails> clientDetails;


    public Map<String, ClientLogin> getClientLogin() {
        return clientLogin;
    }
    public Map<String, ClientDetails> getClientDetails() {
        return clientDetails;
    }


    @Override
    public void createUser(String email, UserLogin userLogin, ClientProfile clientProfile)
            throws UserAlreadyExistsException {
        if (validateEmail(email)) {
            throw new UserAlreadyExistsException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getClientsDatabaseDaoBean().getClientDetails().put(email, (ClientDetails) clientProfile);
        RepositoryOfUsers.getClientsDatabaseDaoBean().getClientLogin().put(email, (ClientLogin) userLogin);
    }


    @Override
    public String readUser(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getClientsDatabaseDaoBean().getClientLogin().get(email).toString() +
                RepositoryOfUsers.getClientsDatabaseDaoBean().getClientDetails().get(email).toString();
    }


    @Override
    public void deleteUser(String email) throws NoSuchUserException{
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getClientsDatabaseDaoBean().getClientLogin().remove(email);
        RepositoryOfUsers.getClientsDatabaseDaoBean().getClientDetails().remove(email);
    }


    @Override
    public boolean validateEmail(String email){
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getClientsDatabaseDaoBean().getClientLogin().containsKey(email);
    }

}