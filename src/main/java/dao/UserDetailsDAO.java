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

    public List<UserLogin> getProfBySurname(String surname);
    public List<UserLogin> getProfByCity(City city);
    public List<UserLogin> getProfByProfession(TypeOfProfession profession);

}
