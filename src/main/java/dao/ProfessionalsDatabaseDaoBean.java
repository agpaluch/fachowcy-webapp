package dao;


import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;
import repository.RepositoryOfUsers;

import java.util.Map;


public class ProfessionalsDatabaseDaoBean implements UserCRUDDao {

    private Map<String, ProfessionalLogin> professionalLogin;
    private Map<String, ProfessionalDetails> professionalDetails;


    public Map<String, ProfessionalLogin> getProfessionalLogin() {
        return professionalLogin;
    }

    public Map<String, ProfessionalDetails> getProfessionalDetails() {
        return professionalDetails;
    }


    @Override
    public void createUser(String email, UserLogin userLogin, ClientProfile clientProfile)
            throws UserAlreadyExistsException {
        if (validateEmail(email)){
            throw new UserAlreadyExistsException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalDetails().put(email, (ProfessionalDetails) clientProfile);
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalLogin().put(email, (ProfessionalLogin) userLogin);
    }

    @Override
    public String readUser(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalLogin().get(email).toString()+
         RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalDetails().get(email).toString();


    }

    @Override
    public void deleteUser(String email) throws NoSuchUserException{
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalLogin().remove(email);
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalDetails().remove(email);
    }

    @Override
    public boolean validateEmail(String email){
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getProfessionalLogin().containsKey(email);
    }

}
