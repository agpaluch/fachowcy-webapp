package servlets;

import dao.ProfessionalDetails;
import dao.ProfessionalLogin;
import exceptions.NoSuchUserException;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.City;
import repository.CityDistrict;
import repository.RepositoryOfUsers;
import repository.TypeOfProfession;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

@WebServlet("/signup-prof")
public class SignupProf extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";


    @Override
    public void init() {
        try {
            template = TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME);
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

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> mapOfErrors = Stream.concat(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ProfessionalLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));

        dataMap.put("content", "signup-prof");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
        dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
        dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        dataMap.put("errors", mapOfErrors);


       //printWriter.write(mapOfErrors.keySet().toString());

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



        //TODO: check if at the beginning of the phone number there is "+" and change it into "00"
        String phoneNumberString = req.getParameter("phoneNumber");
        long phoneNumber = Long.parseLong(phoneNumberString);

        String cityString = req.getParameter("city");
        City city = City.valueOf(cityString);

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

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ProfessionalDetails>> constraintViolationSet = validator.validate(professionalDetails);
        Set<ConstraintViolation<ProfessionalLogin>> constraintViolationSet1 = validator.validate(professionalLogin);


        PrintWriter printWriter = resp.getWriter();
        //printWriter.write(professionalLogin.toString() + professionalDetails.toString());

        //resp.sendRedirect("/details-prof");







        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> mapOfErrors = Stream.concat(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ProfessionalLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));

        for (ConstraintViolation<ProfessionalLogin> constraintViolation: constraintViolationSet1){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }

        for (ConstraintViolation<ProfessionalDetails> constraintViolation: constraintViolationSet){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }
        //printWriter.write(mapOfErrors.values().toString());
        dataMap.put("content", "signup-prof");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
        dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
        dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        dataMap.put("errors", mapOfErrors);


/*        for (ConstraintViolation<ProfessionalDetails> constraintViolation: constraintViolationSet){
            printWriter.write(constraintViolation.getPropertyPath().toString()+constraintViolation.getMessage());
        }

        for (ConstraintViolation<ProfessionalLogin> constraintViolation: constraintViolationSet1){
            printWriter.write(constraintViolation.getPropertyPath().toString()+constraintViolation.getMessage());
        }*/

        try {
            template.process(dataMap, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }


    }




}