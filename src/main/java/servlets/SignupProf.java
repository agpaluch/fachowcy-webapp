package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import daoOld.UserDao;
import domain.UserDTO;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.City;
import repository.TypeOfProfession;
import session.SessionInfo;

import javax.inject.Inject;
import javax.inject.Named;
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

@WebServlet("/signup-prof")
public class SignupProf extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Template template;
    private Map<String, Object> dataMap = new HashMap<>();
    private Map<String, String> mapOfErrors = new HashMap<>();
    private Map<String, Object> mapOfValues = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();


    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @Inject
    @Named("usersDatabase")
    UserDao userDao;



    @Override
    public void init() {


        mapOfValues = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        mapOfErrors = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));


        dataMap.put("content", "signup-prof");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
        dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
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



/*        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //objectMapper.writeValue(resp.getOutputStream(), mapOfErrors);

        //ObjectMapper obj = new ObjectMapper();
        resp.setStatus(200);
        //resp.getWriter().print(obj.writeValueAsString(mapOfErrors));

        objectMapper.writeValue(resp.getOutputStream(), new UserDTO());*/


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        UserDTO userDTO = objectMapper.readValue(req.getInputStream(), UserDTO.class);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);

        mapOfErrors = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        for (ConstraintViolation<UserDTO> constraintViolation: constraintViolations){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }


 /*       resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //objectMapper.writeValue(resp.getOutputStream(), mapOfErrors);

        //ObjectMapper obj = new ObjectMapper();
        resp.setStatus(200);
        //resp.getWriter().print(obj.writeValueAsString(mapOfErrors));

        objectMapper.writeValue(resp.getOutputStream(), userDTO);*/







        mapOfValues.put("email", userDTO.getEmail());
        mapOfValues.put("password", userDTO.getPassword());
        mapOfValues.put("confirmPassword", userDTO.getConfirmPassword());
        mapOfValues.put("name", userDTO.getName());
        mapOfValues.put("surname", userDTO.getSurname());
        mapOfValues.put("profession", userDTO.getProfession());
        mapOfValues.put("longitude", userDTO.getLongitude());
        mapOfValues.put("latitude", userDTO.getLatitude());


        dataMap.put("errors", mapOfErrors);
        dataMap.put("inputData", mapOfValues);

        resp.setContentType("text/html; charset=utf-8");
        resp.setStatus(200);

        PrintWriter printWriter = resp.getWriter();



        if (constraintViolations.isEmpty()){
            // TODO: stworzyć właściwego użytkownika, sprawdzić czy taki mail istnieje w bazie
            //i jeśli nie to zapisać użytkownika do bazy danych
            //Jeśli istnieje to przekierować do widoku z komunikatem, że użytkownik o takim
            //emailu ma już konto.
            sessionInfo.setUserType("professional");
            resp.sendRedirect("/login-form");
        } else {
            try {
                template.process(dataMap, printWriter);
            } catch (TemplateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }


   /*     if (constraintViolations.isEmpty() && (constraintViolationsPassword.isEmpty())){
            long phoneNumber = Long.parseLong(phoneNumberString);
            City city = City.valueOf(cityString);
            //CityDistrict cityDistrict = CityDistrict.valueOf(cityDistrictString);
            TypeOfProfession profession = TypeOfProfession.valueOf(professionString);
            Double longitude = Double.parseDouble(longitudeString);
            Double latitude = Double.parseDouble(latitudeString);

            UserLogin userLogin = new UserLogin(email, password);
            UserDetails userDetails = new UserDetails(name, surname, profession, phoneNumber,
                                                        city, longitude, latitude);

            try {
                userDao.createUser(email, userLogin, userDetails);
                sessionInfo.setUserType("professional");
            } catch (UserAlreadyExistsException e){
            //TODO: handle this exception
            }


            resp.sendRedirect("/login-form");
        } else {

            try {
                template.process(dataMap, printWriter);
            } catch (TemplateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }*/



    }


}