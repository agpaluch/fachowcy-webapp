package dao;


import domain.ClientProfile;
import domain.UserLogin;
import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface UserCRUDDao {

    Map<String, ?> getLogin();

    Map<String, ?> getDetails();

    void createUser(String email, UserLogin userLogin, ClientProfile clientProfile) throws UserAlreadyExistsException;


    UserLogin findUserLogin(String email) throws NoSuchUserException;

    ClientProfile findUserDetails(String email) throws NoSuchUserException;


    default void updateUser(String email, UserLogin userLogin, ClientProfile clientProfile)
            throws NoSuchUserException, UserAlreadyExistsException{
        deleteUser(email);
        createUser(email, userLogin, clientProfile);
    }

    void deleteUser(String email) throws NoSuchUserException;


    boolean validateEmail(String email);
        //returns true if a given e-mail already exists in the database

    boolean isAuthorized(String email, String password);


}
