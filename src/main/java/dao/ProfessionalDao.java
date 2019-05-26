package dao;

import domain.ProfessionalDetails;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProfessionalDao extends UserCRUDDao {

    public List<ProfessionalDetails> getByProfession(String profession);

}
