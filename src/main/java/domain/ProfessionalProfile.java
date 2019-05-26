package domain;


import repository.TypeOfProfession;

public interface ProfessionalProfile extends ClientProfile {

    TypeOfProfession getProfession();

    void setProfession(TypeOfProfession profession);

}
