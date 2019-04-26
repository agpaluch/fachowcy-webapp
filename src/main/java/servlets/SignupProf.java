package servlets;

import dao.PasswordDto;
import dao.ProfessionalDetails;
import dao.ProfessionalDto;
import dao.ProfessionalLogin;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.*;

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



        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> mapOfErrors = Stream.concat(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ProfessionalLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));
        mapOfErrors.put("confirmPassword", "");

        /*Map<String, String> mapOfValues = Stream.concat(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ProfessionalLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));*/

        dataMap.put("content", "signup-prof");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
        dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
        dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        dataMap.put("errors", mapOfErrors);
        //dataMap.put("correctValues", mapOfValues);



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
        String phoneNumberString = req.getParameter("phoneNumber").replace("-","");
        String cityString = req.getParameter("city");
        String cityDistrictString = req.getParameter("cityDistrict");
        String professionString = req.getParameter("profession");
        String longitudeString = req.getParameter("longitude");
        String latitudeString = req.getParameter("latitude");

        ProfessionalDto professionalDto = new ProfessionalDto(name, surname, professionString,
                phoneNumberString, cityString, cityDistrictString, longitudeString, latitudeString,
                email);
        PasswordDto passwordDto = new PasswordDto(password, confirmPassword);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ProfessionalDto>> constraintViolationsDetails =
                validator.validate(professionalDto);
        Set<ConstraintViolation<PasswordDto>> constraintViolationsPassword =
                validator.validate(passwordDto);


        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> mapOfErrors = Stream.concat(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ProfessionalLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));


        for (ConstraintViolation<ProfessionalDto> constraintViolation: constraintViolationsDetails){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }

        if (!(constraintViolationsPassword.size()==0)){
            for (ConstraintViolation<PasswordDto> constraintViolation: constraintViolationsPassword){
                mapOfErrors.put("confirmPassword", constraintViolation.getMessage());
            }
        } else {
                mapOfErrors.put("confirmPassword", "");
        }


        PrintWriter printWriter = resp.getWriter();

        if (constraintViolationsDetails.isEmpty() && (constraintViolationsPassword.size()==0)){
            long phoneNumber = Long.parseLong(phoneNumberString);
            City city = City.valueOf(cityString);
            CityDistrict cityDistrict = CityDistrict.valueOf(cityDistrictString);
            TypeOfProfession profession = TypeOfProfession.valueOf(professionString);
            Double longitude = Double.parseDouble(longitudeString);
            Double latitude = Double.parseDouble(latitudeString);

            ProfessionalLogin professionalLogin = new ProfessionalLogin(email, password);
            ProfessionalDetails professionalDetails = new ProfessionalDetails(name, surname, profession, phoneNumber, city, cityDistrict, longitude, latitude);
            printWriter.write(professionalDetails.toString() +"\n" + professionalLogin.toString());

            //resp.sendRedirect("/details-prof");
        } else {
            dataMap.put("content", "signup-prof");
            dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
            dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
            dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
            dataMap.put("errors", mapOfErrors);

            try {
                template.process(dataMap, printWriter);
            } catch (TemplateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }





        //TODO: check if at the beginning of the phone number there is "+" and change it into "00"

        /*long phoneNumber = Long.parseLong(phoneNumberString);


        City city = City.valueOf(cityString);


        CityDistrict cityDistrict = CityDistrict.valueOf(cityDistrictString);


        TypeOfProfession profession = TypeOfProfession.valueOf(professionString);


        Double longitude = Double.parseDouble(longitudeString);


        Double latitude = Double.parseDouble(latitudeString);


        ProfessionalLogin professionalLogin = new ProfessionalLogin(email, password);
        ProfessionalDetails professionalDetails = new ProfessionalDetails(name, surname, profession, phoneNumber, city, cityDistrict, longitude, latitude);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ProfessionalDetails>> constraintViolationSet = validator.validate(professionalDetails);
        Set<ConstraintViolation<ProfessionalLogin>> constraintViolationSet1 = validator.validate(professionalLogin);


        PrintWriter printWriter = resp.getWriter();
*//*
        Map<String, String> mapOfValues = req.getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()[0]));*//*



        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> mapOfErrors = Stream.concat(Arrays.stream(ProfessionalDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ProfessionalLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));

        for (ConstraintViolation<ProfessionalLogin> constraintViolation: constraintViolationSet1){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
           // mapOfValues.put(constraintViolation.getPropertyPath().toString(), "");
        }

        for (ConstraintViolation<ProfessionalDetails> constraintViolation: constraintViolationSet){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
            //mapOfValues.put(constraintViolation.getPropertyPath().toString(), "");
        }


        dataMap.put("content", "signup-prof");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
        dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
        dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        dataMap.put("errors", mapOfErrors);
        //dataMap.put("correctValues", mapOfValues);

        printWriter.write(mapOfErrors.keySet().toString());

 *//*       try {
            template.process(dataMap, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }*//*

*/





    }




}