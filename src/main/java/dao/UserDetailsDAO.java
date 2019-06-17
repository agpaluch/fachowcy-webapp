package dao;

import domain.UserDetails;
import repository.City;
import javax.ejb.Local;

@Local
public interface UserDetailsDAO {

    public UserDetails getByName(String name);
    public UserDetails getByCity(City city);
    public UserDetails getByProfession(String profession);

}
