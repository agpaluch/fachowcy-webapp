package daoOld;

import domain.UserDetails;
import domain.UserLogin;
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
@Named("usersDatabase")
public class UserDaoBean implements UserDao, Serializable {

    private Map<String, UserLogin> userLogin;
    private Map<String, UserDetails> userDetails;

    @Override
    public Map<String, UserLogin> getLogin() {
        return userLogin;
    }

    @Override
    public Map<String, UserDetails> getDetails() {
        return userDetails;
    }


    @Override
    public void createUser(String email, UserLogin userLogin, UserDetails userDetails)
            throws UserAlreadyExistsException {
        if (validateEmail(email)){
            throw new UserAlreadyExistsException();
        }
        RepositoryOfUsers.fillDatabase();
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getDetails().put(email, userDetails);
        RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().put(email, userLogin);
    }



    @Override
    public UserLogin findUserLogin(String email) throws NoSuchUserException {
        if (!validateEmail(email)){
            throw new NoSuchUserException();
        }
        RepositoryOfUsers.fillDatabase();
        return RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getLogin().get(email);
    }

    @Override
    public UserDetails findUserDetails(String email) throws NoSuchUserException {
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
    public List<UserDetails> getByProfession(String profession){

        RepositoryOfUsers.fillDatabase();
        List<UserDetails> values =
                new ArrayList<>(RepositoryOfUsers.getProfessionalsDatabaseDaoBean().getDetails().values());

        return values.stream().filter(pd -> pd.getProfession().toString()
                .equalsIgnoreCase(profession)).collect(Collectors.toList());
    }


}
