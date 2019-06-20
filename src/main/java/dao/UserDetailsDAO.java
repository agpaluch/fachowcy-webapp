package dao;

import domain.UserDetails;
import domain.UserLogin;
import repository.City;
import repository.TypeOfProfession;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserDetailsDAO {

    public List<UserLogin> getBySurname(String surname);
    public List<UserLogin> getByCity(City city);


}
