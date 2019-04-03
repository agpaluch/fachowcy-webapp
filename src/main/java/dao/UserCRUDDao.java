package dao;


import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;

import javax.ejb.Local;

@Local
public interface UserCRUDDao {

    void createUser(String email, UserLogin userLogin, ClientProfile clientProfile) throws UserAlreadyExistsException;

    String readUser(String email) throws NoSuchUserException;

    default void updateUser(String email, UserLogin userLogin, ClientProfile clientProfile)
            throws NoSuchUserException, UserAlreadyExistsException{
        deleteUser(email);
        createUser(email, userLogin, clientProfile);
    }

    void deleteUser(String email) throws NoSuchUserException;


    boolean validateEmail(String email);
        //returns true if a given e-mail already exists in the database





}
