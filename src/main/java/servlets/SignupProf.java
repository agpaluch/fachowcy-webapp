package servlets;

import dao.ProfessionalDetails;
import dao.ProfessionalLogin;
import exceptions.NoSuchUserException;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.CityDistrict;
import repository.RepositoryOfUsers;
import repository.TypeOfProfession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet("/signup-prof")
public class SignupProf extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    @Override
    public void init() {
        try {
            template = TemplateProvider.createTemplate(getServletContext(), "signup-prof.ftlh");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


   /*     req.setAttribute("errorEmail", "");
        req.setAttribute("errorPassword", "");*/

  /*      RequestDispatcher rd = req.getRequestDispatcher("signup-prof.ftlh");
        rd.forward(req, resp);*/


        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, List<Object>> dataMap = new HashMap<>();
        dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
        dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        

        try {
            template.process(dataMap, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");



        String phoneNumberString = req.getParameter("phoneNumber");
        int phoneNumber = Integer.parseInt(phoneNumberString);

        String city = req.getParameter("city");

        String cityDistrictString = req.getParameter("cityDistrict");
        CityDistrict cityDistrict = CityDistrict.valueOf(cityDistrictString);

        String professionString = req.getParameter("profession");
        TypeOfProfession profession = TypeOfProfession.valueOf(professionString);

        String longitudeString = req.getParameter("longitude");
        Double longitude = Double.parseDouble(longitudeString);
        String latitudeString = req.getParameter("latitude");
        Double latitude = Double.parseDouble(latitudeString);


        ProfessionalLogin professionalLogin = new ProfessionalLogin(email, password);
        ProfessionalDetails professionalDetails = new ProfessionalDetails(name, surname, profession, phoneNumber, city, cityDistrict, longitude, latitude);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(professionalLogin.toString() + professionalDetails.toString());

/*        RepositoryOfUsers.fillDatabase();
        try {
            printWriter.write(RepositoryOfUsers.getProfessionalsDatabaseDaoBean().readUser("ccc@gmail.com"));
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }*/



    }




}