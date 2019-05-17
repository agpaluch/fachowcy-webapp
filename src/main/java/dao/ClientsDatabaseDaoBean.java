package dao;


import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;
import repository.RepositoryOfUsers;

import javax.ejb.Stateful;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Stateful
@Named("clientsDatabase")
public class ClientsDatabaseDaoBean implements UserCRUDDao, Serializable {

    private Map<String, ClientLogin> clientLogin;
    private Map<String, ClientDetails> clientDetails;


    public Map<String, ClientLogin> getLogin() {
        return clientLogin;
    }
    public Map<String, ClientDetails> getDetails() {
        return clientDetails;
    }


    @Override
    public void createUser(String email, UserLogin userLogin, ClientProfile clientProfile)
            throws UserAlreadyExistsException {
        if (validateEmail(email)) {
            throw new UserAlreadyExistsException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getClientsDatabaseDaoBean().getDetails().put(email, (ClientDetails) clientProfile);
        RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().put(email, (ClientLogin) userLogin);
    }


    @Override
    public UserLogin findUserLogin(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().get(email);
    }

    @Override
    public ClientDetails findUserDetails(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getClientsDatabaseDaoBean().getDetails().get(email);
    }


    @Override
    public void deleteUser(String email) throws NoSuchUserException{
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().remove(email);
        RepositoryOfUsers.getClientsDatabaseDaoBean().getDetails().remove(email);
    }


    @Override
    public boolean validateEmail(String email){
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().containsKey(email);
    }

    @Override
    public boolean isAuthorized(String email, String password){
        RepositoryOfUsers.fillDatabase();

        return RepositoryOfUsers.getClientsDatabaseDaoBean()
                .getLogin()
                .values()
                .stream()
                .anyMatch(a -> a.getEmail().equals(email) && a.getPassword().equals(password));

    }

}
