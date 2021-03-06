package servlets;

import dao.ProfessionsDAO;
import dao.UserLoginDAO;
import domain.*;
import freemarker.template.Template;
import repository.City;
import session.SessionInfo;
import template.TemplateProvider;
import template.TemplateProxy;

import javax.ejb.EJB;
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
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet("/signup")
public class Signup extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;
    private Map<String, Object> dataMap = new HashMap<>();
    private Map<String, String> mapOfErrors = new HashMap<>();
    private Map<String, Object> mapOfValues = new HashMap<>();

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @EJB
    ProfessionsDAO professionsDAO;

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

        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Role role = Role.valueOf(req.getParameter("role"));

        if (role == Role.PROFESSIONAL){
            dataMap.put("professions", professionsDAO.getAll());
        } else {
            dataMap.put("professions",null);
        }

        templateProxy.freemarkerEngine(dataMap, resp);


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
        String profession = null;
        Role role;

        try {
            phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
            city = City.valueOf(req.getParameter("city"));
            longitude = Double.parseDouble(req.getParameter("longitude"));
            latitude = Double.parseDouble(req.getParameter("latitude"));
        } catch (IllegalArgumentException e) {
            resp.setStatus(500);
            logger.log(Level.SEVERE, "Niepoprawne dane wejściowe. Nie powinny przejść " +
                    "walidacji przez Java Script.");
        }


        if (req.getParameter("profession")!=null){
                role = Role.PROFESSIONAL;
                try{
                    profession = req.getParameter("profession");
                } catch (IllegalArgumentException e) {
                    resp.setStatus(500);
                    logger.log(Level.SEVERE, "Niepoprawne dane wejściowe. Nie powinny przejść " +
                            "walidacji przez Java Script.");
                }
            } else {
                role = Role.CLIENT;
            }

            UserDTO userDTO = UserDTO.builder()
                    .email(email)
                    .password(password)
                    .confirmPassword(confirmPassword)
                    .role(role)
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

                Professions profToBeAdded;
                if(role == Role.CLIENT) {
                    profToBeAdded = null;
                } else {
                    profToBeAdded = saveProfessionInTheProfessionsTable(profession);
                }

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
                        .profession(profToBeAdded)
                        .build();

                if (userLoginDAO.doesAUserExist(email)){
                    printWriter.write("Użytkownik o podanym adresie e-mail juz istnieje w bazie.");
                } else {
                    userLoginDAO.save(userLogin);
                    resp.sendRedirect("/login-form");
                }


            } else {

                templateProxy.freemarkerEngine(dataMap, resp);

            }

        }



    private Professions saveProfessionInTheProfessionsTable(String profession) {

        Optional<Professions> maybeProfession = professionsDAO.getByProfession(profession);
        Professions profToBeAdded;

        if(maybeProfession.isPresent()) {
            profToBeAdded = Professions.builder()
                    .id(maybeProfession.get().getId())
                    .profession(profession)
                    .build();
        } else {
            profToBeAdded = Professions.builder()
                    .id(null)
                    .profession(profession)
                    .build();
            professionsDAO.save(profToBeAdded);
        }
        return profToBeAdded;
    }

}