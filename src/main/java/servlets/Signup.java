package servlets;

import dao.UserLoginDAO;
import dao.UserLoginDAOBean;
import domain.*;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.City;
import repository.TypeOfProfession;
import session.SessionInfo;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
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
    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;


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

        template = FreemarkerUtil.createTemplate(TEMPLATE_NAME, logger, getServletContext());

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Role role = Role.valueOf(req.getParameter("role"));

        if (role==Role.PROFESSIONAL){
            dataMap.put("professions", Arrays.stream(TypeOfProfession.values()).collect(Collectors.toList()));
        } else {
            dataMap.put("professions",null);
        }

        FreemarkerUtil.processData(resp, template, dataMap, logger);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Long phoneNumber = null;
        City city = null;
        Double longitude = null;
        Double latitude = null;
        TypeOfProfession profession = null;
        Role role;


        try {
            phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
            city = City.valueOf(req.getParameter("city"));
            longitude = Double.parseDouble(req.getParameter("longitude"));
            latitude = Double.parseDouble(req.getParameter("latitude"));
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Niepoprawne dane wejściowe. Nie powinny przejść " +
                    "walidacji przez Java Script.");
        } finally{

            if (req.getParameter("profession")!=null){
                role = Role.PROFESSIONAL;
                try{
                    profession = TypeOfProfession.valueOf(req.getParameter("profession"));
                } catch (IllegalArgumentException e) {
                    resp.setStatus(500);
                }
            } else {
                role = Role.CLIENT;
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

                UserDetails userDetails = UserDetails.builder()
                        .name(name)
                        .surname(surname)
                        .phoneNumber(phoneNumber)
                        .city(city)
                        .longitude(longitude)
                        .latitude(latitude)
                        .build();


                UserLogin userLogin = UserLogin.builder()
                        .userDetails(userDetails)
                        .email(email)
                        .password(password)
                        .role(role)
                        .profession(new Professions(profession))
                        .build();

                if (userLoginDAO.doesAUserExist(email)){
                    printWriter.write("Użytkownik o podanym adresie e-mail juz istnieje w bazie.");
                } else {
                    userLoginDAO.save(userLogin);
                    resp.sendRedirect("/login-form");
                }



            } else {

                FreemarkerUtil.processData(resp, template, dataMap, logger);

            }


        }





    }



}