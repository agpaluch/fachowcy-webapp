package dao;

import repository.RepositoryOfUsers;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Local
public interface ProfessionalCRUDDao extends UserCRUDDao {

    public List<ProfessionalDetails> getByProfession(String profession);

}
