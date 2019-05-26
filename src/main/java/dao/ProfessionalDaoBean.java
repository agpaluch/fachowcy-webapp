package dao;


import domain.ClientProfile;
import domain.ProfessionalDetails;
import domain.ProfessionalLogin;
import exceptions.NoSuchUserException;
import exceptions.UserAlreadyExistsException;
import repository.RepositoryOfUsers;

import javax.ejb.Stateful;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Stateful
@Named("professionalsDatabase")
public class ProfessionalDaoBean implements ProfessionalDao, Serializable {

    private Map<String, ProfessionalLogin> professionalLogin;
    private Map<String, ProfessionalDetails> professionalDetails;


    public Map<String, ProfessionalLogin> getLogin() {
        return professionalLogin;
    }

    public Map<String, ProfessionalDetails> getDetails() {
        return professionalDetails;
    }


    @Override
    public void createUser(String email, ProfessionalLogin professionalLogin, ClientProfile clientProfile)
            throws UserAlreadyExistsException {
        if (validateEmail(email)){
            throw new UserAlreadyExistsException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getDetails().put(email, (ProfessionalDetails) clientProfile);
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().put(email, professionalLogin);
    }



    @Override
    public ProfessionalLogin findUserLogin(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().get(email);
    }

    @Override
    public ClientProfile findUserDetails(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getDetails().get(email);
    }



    @Override
    public void deleteUser(String email) throws NoSuchUserException{
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().remove(email);
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getDetails().remove(email);
    }


    @Override
    public boolean validateEmail(String email){
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().containsKey(email);
    }

    @Override
    public boolean isAuthorized(String email, String password){
        RepositoryOfUsers.fillDatabase();

        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean()
                .getLogin()
                .values()
                .stream()
                .anyMatch(a -> a.getEmail().equals(email) && a.getPassword().equals(password));

    }


    @Override
    public List<ProfessionalDetails> getByProfession(String profession){

        RepositoryOfUsers.fillDatabase();
        List<ProfessionalDetails> values =
                new ArrayList<>(RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getDetails().values());

        return values.stream().filter(pd -> pd.getProfession().toString()
                .equalsIgnoreCase(profession)).collect(Collectors.toList());
    }


}
