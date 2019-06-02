package daoOld;

import domain.UserDetails;
import domain.UserLogin;
import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface UserDao {

    public List<UserDetails> getByProfession(String profession);

    Map<String, ?> getLogin();

    Map<String, ?> getDetails();

    void createUser(String email, UserLogin userLogin, UserDetails userDetails)
            throws UserAlreadyExistsException;

    UserLogin findUserLogin(String email) throws NoSuchUserException;

    UserDetails findUserDetails(String email) throws NoSuchUserException;

    default void updateUser(String email, UserLogin userLogin, UserDetails userDetails)
            throws NoSuchUserException, UserAlreadyExistsException{
        deleteUser(email);
        createUser(email, userLogin, userDetails);
    }

    void deleteUser(String email) throws NoSuchUserException;

    boolean validateEmail(String email);
    //returns true if a given e-mail already exists in the database

    boolean isAuthorized(String email, String password);

}
