package servlets;

import domain.Role;
import domain.UserDTO;
import domain.UserDetails;
import domain.UserLogin;
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

@WebServlet("/signup")
public class Signup extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Template template;
    private Map<String, Object> dataMap = new HashMap<>();
    private Map<String, String> mapOfErrors = new HashMap<>();
    private Map<String, Object> mapOfValues = new HashMap<>();
    private UserDTO userDTO;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;


    @Override
    public void init() {

        mapOfValues = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        mapOfErrors = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));


        dataMap.put("content", "signup");
        dataMap.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));
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


        Role role = Role.valueOf(req.getParameter("role"));
        sessionInfo.setRole(role);

        if (role==Role.PROFESSIONAL){
            dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        }


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
        long phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
        City city = City.valueOf(req.getParameter("city"));
        Double longitude = Double.parseDouble(req.getParameter("longitude"));
        Double latitude = Double.parseDouble(req.getParameter("latitude"));
        TypeOfProfession profession = null;

        if (req.getParameter("profession")!=null){
            profession = TypeOfProfession.valueOf(req.getParameter("profession"));
        }



        UserDTO userDTO = UserDTO.builder()
                .email(email)
                .password(password)
                .confirmPassword(confirmPassword)
                .name(name)
                .surname(surname)
                .profession(profession)
                .phoneNumber(phoneNumber)
                .city(city)
                .longitude(longitude)
                .latitude(latitude)
                .build();


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set<ConstraintViolation<UserDTO>> constraintViolations =
                validator.validate(userDTO);


        mapOfErrors = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        for (ConstraintViolation<UserDTO> constraintViolation: constraintViolations){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }


        mapOfValues = req.getParameterMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()[0]));

        dataMap.put("errors", mapOfErrors);
        dataMap.put("inputData", mapOfValues);

        PrintWriter printWriter = resp.getWriter();


        if (constraintViolations.isEmpty()){
            // TODO: stworzyć właściwego użytkownika, sprawdzić czy taki mail istnieje w bazie
            //i jeśli nie to zapisać użytkownika do bazy danych
            //Jeśli istnieje to przekierować do widoku z komunikatem, że użytkownik o takim
            //emailu ma już konto.


            UserLogin userLogin = UserLogin.builder()
                    .userDetails(null)
                    .email(email)
                    .password(password)
                    .role(sessionInfo.getRole())
                    .build();

            UserDetails userDetails = UserDetails.builder()
                    //.userLogin(userLogin)
                    .name(name)
                    .surname(surname)
                    .profession(profession)
                    .phoneNumber(phoneNumber)
                    .city(city)
                    .longitude(longitude)
                    .latitude(latitude)
                    .build();

            userLogin.setUserDetails(userDetails);



            //resp.sendRedirect("/login-form");

            printWriter.write(userDTO.toString());

        } else {

            try {
                template.process(dataMap, printWriter);
            } catch (TemplateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }




    }



}