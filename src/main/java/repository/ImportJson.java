package repository;

import com.google.gson.Gson;
import dao.ClientsDatabaseDaoBean;
import dao.ProfessionalsDatabaseDaoBean;

import java.io.*;


public class ImportJson {

    private static ClientsDatabaseDaoBean clientsDatabaseDaoBean;
    private static ProfessionalsDatabaseDaoBean professionalsDatabaseDaoBean;


    public static void readExistingDatabase() {

        //Read existing database of clients from JSON.file and print information about their logins and details
        InputStream inputStreamClient = ImportJson.class.getClassLoader().getResourceAsStream("clientsDatabase.json");
        String contentClients = readFileToString(inputStreamClient);

        clientsDatabaseDaoBean = new Gson().fromJson(contentClients, ClientsDatabaseDaoBean.class);

        //Read existing database of professionals from JSON.file and print information about their logins and details
        InputStream inputStreamProf = ImportJson.class.getClassLoader().getResourceAsStream("professionalsDatabase.json");
        String contentProfessionals = readFileToString(inputStreamProf);

        professionalsDatabaseDaoBean =  new Gson().fromJson(contentProfessionals, ProfessionalsDatabaseDaoBean.class);

    }

    public static String readFileToString(InputStream inputStream){
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


    public static ClientsDatabaseDaoBean getClientsDatabaseDaoBean() {
        //TODO check if not null
        return clientsDatabaseDaoBean;
    }

    public static ProfessionalsDatabaseDaoBean getProfessionalsDatabaseDaoBean() {
        //TODO check if not null
        return professionalsDatabaseDaoBean;
    }






}
