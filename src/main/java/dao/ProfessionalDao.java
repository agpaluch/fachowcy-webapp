package dao;

import domain.ClientProfile;
import domain.ProfessionalDetails;
import domain.ProfessionalLogin;
import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface ProfessionalDao {

    public List<ProfessionalDetails> getByProfession(String profession);

    Map<String, ?> getLogin();

    Map<String, ?> getDetails();

    void createUser(String email, ProfessionalLogin professionalLogin, ClientProfile clientProfile)
            throws UserAlreadyExistsException;

    ProfessionalLogin findUserLogin(String email) throws NoSuchUserException;

    ClientProfile findUserDetails(String email) throws NoSuchUserException;

    default void updateUser(String email, ProfessionalLogin professionalLogin, ClientProfile clientProfile)
            throws NoSuchUserException, UserAlreadyExistsException{
        deleteUser(email);
        createUser(email, professionalLogin, clientProfile);
    }

    void deleteUser(String email) throws NoSuchUserException;

    boolean validateEmail(String email);
    //returns true if a given e-mail already exists in the database

    boolean isAuthorized(String email, String password);

}
