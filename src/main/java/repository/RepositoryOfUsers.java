package repository;

import dao.ClientsDatabaseDaoBean;
import dao.ProfessionalsDatabaseDaoBean;


public class RepositoryOfUsers {

    private static ClientsDatabaseDaoBean clientsDatabaseDaoBean;
    private static ProfessionalsDatabaseDaoBean professionalsDatabaseDaoBean;

    public static void fillDatabase(){

        ImportJson.readExistingDatabase();

        if (clientsDatabaseDaoBean==null){
            clientsDatabaseDaoBean = ImportJson.getClientsDatabaseDaoBean();
        }

        if (professionalsDatabaseDaoBean==null){
            professionalsDatabaseDaoBean = ImportJson.getProfessionalsDatabaseDaoBean();
        }

    }

    public static ClientsDatabaseDaoBean getClientsDatabaseDaoBean() {
        return clientsDatabaseDaoBean;
    }

    public static ProfessionalsDatabaseDaoBean getProfessionalsDatabaseDaoBean() {
        return professionalsDatabaseDaoBean;
    }
}
