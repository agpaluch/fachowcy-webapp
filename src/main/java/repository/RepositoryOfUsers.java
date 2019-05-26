package repository;

import com.google.gson.Gson;
import dao.ProfessionalsDatabaseDaoBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RepositoryOfUsers {

    private static ProfessionalsDatabaseDaoBean professionalsDatabaseDaoBean;

    public static void fillDatabase(){

        if (professionalsDatabaseDaoBean==null){
            RepositoryOfUsers.readExistingDatabase();
        }

    }

    public static ProfessionalsDatabaseDaoBean getProfessionalsDatabaseDaoBean() {
        return professionalsDatabaseDaoBean;
    }


    private static void readExistingDatabase() {

        //Read existing database of professionals from JSON.file and print information about their logins and details
        InputStream inputStreamProf = RepositoryOfUsers.class.getClassLoader().getResourceAsStream("professionalsDatabase.json");
        String contentProfessionals = readFileToString(inputStreamProf);

        professionalsDatabaseDaoBean =  new Gson().fromJson(contentProfessionals, ProfessionalsDatabaseDaoBean.class);
    }

    private static String readFileToString(InputStream inputStream){
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            while (line != null){
                sb.append(line);
                line = in.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
