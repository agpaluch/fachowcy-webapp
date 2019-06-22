package servlets;

import dao.UserLoginDAO;
import domain.*;
import freemarker.template.Template;
import repository.City;
import repository.TypeOfProfession;
import session.SessionInfo;

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
import java.util.stream.Stream;

@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet{

    private Logger logger = Logger.getLogger(getClass().getName());
    private Template template;
    private Map<String, Object> dataMap = new HashMap<>();
    private Map<String, String> mapOfErrors = new HashMap<>();
    private Map<String, Object> mapOfValues = new HashMap<>();
    private UserLogin userToEdit;
    private static final String TEMPLATE_NAME = "index";


    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;


    public void init(){
        template = FreemarkerUtil.createTemplate(TEMPLATE_NAME, logger, getServletContext());

        List<Field> fields = Stream.concat(Arrays.stream(UserLogin.class.getDeclaredFields()),
                Arrays.stream(UserDetails.class.getDeclaredFields()))
                .filter(f -> !f.getName().equals("userDetails"))
                .filter(f -> !f.getName().equals("id"))
                .collect(Collectors.toList());

        mapOfErrors = fields.stream().map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        dataMap.put("content", "edit-user-trial");
        dataMap.put("sessionInfo", sessionInfo);

    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        String emailToEdit = req.getParameter("emailOfUserToEdit");
        Optional<UserLogin> user = userLoginDAO.getByLogin(emailToEdit);

        if (user.isPresent()){

            userToEdit = user.get();

            dataMap = SinupEditUtil.addCitiesAndProfessions(dataMap, userToEdit.getRole());
            dataMap.put("roles", Arrays.stream(Role.values()).collect(Collectors.toList()));
            dataMap.put("inputData", userToEdit);
            dataMap.put("errors", mapOfErrors);
        }

        FreemarkerUtil.processData(resp, template, dataMap, logger);

    }



    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String professionString = req.getParameter("profession");
        Long phoneNumber = null;
        TypeOfProfession profession = null;
        Role role = null;
        Integer numberOfLikes = null;
        City city = null;
        Double longitude = null;
        Double latitude = null;

        String cityString = req.getParameter("city");
        String longitudeString = req.getParameter("longitude");
        String latitudeString = req.getParameter("latitude");
        String numberOfLikesString = req.getParameter("numberOfLikes");
        String phoneNumberString = req.getParameter("phoneNumber");




        try {
            phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));
            role = Role.valueOf(req.getParameter("role"));
            numberOfLikes = Integer.parseInt(req.getParameter("numberOfLikes"));
            city = City.valueOf(req.getParameter("city"));
            longitude = Double.parseDouble(req.getParameter("longitude"));
            latitude = Double.parseDouble(req.getParameter("latitude"));

            System.out.println("uwgaa");
            if(professionString!=null){
            profession = TypeOfProfession.valueOf(professionString);
            }

        } catch (IllegalArgumentException e) {
            resp.setStatus(500);
            logger.log(Level.SEVERE, "Niepoprawne dane wejściowe. Nie powinny przejść " +
                    "walidacji przez Java Script.");
        }


        UserLogin modifiedUser = userToEdit;
        modifiedUser.setConfirmPassword(modifiedUser.getPassword());

        modifiedUser.setEmail(email);
        modifiedUser.setRole(role);
        if (professionString != null){
            modifiedUser.getProfession().setProfession(profession);
        }
        modifiedUser.getUserDetails().setName(name);
        modifiedUser.getUserDetails().setName(surname);
        modifiedUser.getUserDetails().setPhoneNumber(phoneNumber);
        modifiedUser.getUserDetails().setCity(city);
        modifiedUser.getUserDetails().setNumberOfLikes(numberOfLikes);
        modifiedUser.getUserDetails().setLatitude(latitude);
        modifiedUser.getUserDetails().setLongitude(longitude);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserLogin>> constraintViolations =
                validator.validate(modifiedUser);


        for (ConstraintViolation<UserLogin> constraintViolation: constraintViolations){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }


        dataMap.put("errors", mapOfErrors);
        dataMap.put("inputData", modifiedUser);

        PrintWriter printWriter = resp.getWriter();


        if (constraintViolations.isEmpty()){

                userLoginDAO.save(modifiedUser);
                resp.sendRedirect("/login-form");

        } else {

            FreemarkerUtil.processData(resp, template, dataMap, logger);

        }




    }
}
