package servlets;

import dao.*;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.City;
import repository.TypeOfProfession;
import session.SessionInfo;

import javax.inject.Inject;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/signup-client")
public class SignupClient extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;
    Map<String, Object> dataMap = new HashMap<>();
    Map<String, String> mapOfErrors = new HashMap<>();
    Map<String, String> mapOfValues = new HashMap<>();

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;


    @Override
    public void init() {
        mapOfValues = Stream.concat(Arrays.stream(ClientDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ClientLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));
        mapOfValues.put("confirmPassword", "");

        mapOfErrors = Stream.concat(Arrays.stream(ClientDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ClientLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));
        mapOfErrors.put("confirmPassword", "");

        dataMap.put("content", "signup-client");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
        //dataMap.put("districts", Arrays.stream(CityDistrict.values()).collect(Collectors.toList()));
        dataMap.put("errors", mapOfErrors);
        dataMap.put("inputData", mapOfValues);
        dataMap.put("sessionInfo", sessionInfo);

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
        String phoneNumberString = req.getParameter("phoneNumber").replace("-","")
                                    .replace("+", "00");
        String cityString = req.getParameter("city");
        //String cityDistrictString = req.getParameter("cityDistrict");
        String longitudeString = req.getParameter("longitude");
        String latitudeString = req.getParameter("latitude");


        ClientDto clientDto = new ClientDto(name, surname,
                phoneNumberString, cityString, longitudeString, latitudeString,
                email, password);

        PasswordDto passwordDto = new PasswordDto(password, confirmPassword);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ValidatorFactory factory2 = Validation.buildDefaultValidatorFactory();
        Validator validator2 = factory2.getValidator();

        Set<ConstraintViolation<ClientDto>> constraintViolationsDetails =
                validator.validate(clientDto);
        Set<ConstraintViolation<PasswordDto>> constraintViolationsPassword =
                validator2.validate(passwordDto);

        mapOfErrors = Stream.concat(Arrays.stream(ClientDetails.class.getDeclaredFields())
                .map(Field::getName), Arrays.stream(ClientLogin.class.getDeclaredFields())
                .map(Field::getName)).collect(Collectors.toMap(Function.identity(), n -> ""));
        mapOfErrors.put("confirmPassword", "");

        for (ConstraintViolation<ClientDto> constraintViolation: constraintViolationsDetails){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }

        if (!(constraintViolationsPassword.isEmpty())){
            for (ConstraintViolation<PasswordDto> constraintViolation: constraintViolationsPassword){
                mapOfErrors.put("confirmPassword", constraintViolation.getMessage());
            }
        } else {
            mapOfErrors.put("confirmPassword", "");
        }

        mapOfValues = req.getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()[0]));

        dataMap.put("errors", mapOfErrors);
        dataMap.put("inputData", mapOfValues);

        PrintWriter printWriter = resp.getWriter();




        if (constraintViolationsDetails.isEmpty() && (constraintViolationsPassword.isEmpty())){
            long phoneNumber = Long.parseLong(phoneNumberString);
            City city = City.valueOf(cityString);
            //CityDistrict cityDistrict = CityDistrict.valueOf(cityDistrictString);
            Double longitude = Double.parseDouble(longitudeString);
            Double latitude = Double.parseDouble(latitudeString);

            ClientLogin clientLogin = new ClientLogin(email, password);
            ClientDetails clientDetails = new ClientDetails(name, surname, phoneNumber, city, longitude, latitude);
            printWriter.write(clientDetails.toString() +"\n" + clientLogin.toString());

            //resp.sendRedirect("/details-prof");
        } else {

            try {
                template.process(dataMap, printWriter);
            } catch (TemplateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }




    }



}