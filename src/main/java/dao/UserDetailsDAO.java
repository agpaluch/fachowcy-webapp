package dao;

import domain.UserDetails;
import repository.City;
import javax.ejb.Local;

@Local
public interface UserDetailsDAO extends IRootDAO<UserDetails> {

    public UserDetails getByName(String name);
    public UserDetails getbyCity(City city);
    public UserDetails getByProfession(String profession);

}
