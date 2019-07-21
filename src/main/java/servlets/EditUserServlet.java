package servlets;

import dao.ProfessionsDAO;
import dao.UserLoginDAO;
import domain.*;
import exceptions.FrontEndFormValidationException;
import freemarker.template.Template;
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
    private UserDTO userToEdit;
    private static final String TEMPLATE_NAME = "index";
    private Long idOfUserToEdit;


    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @EJB
    ProfessionsDAO professionsDAO;


    public void init(){
        template = FreemarkerUtil.createTemplate(TEMPLATE_NAME, logger, getServletContext());

        List<Field> fields = Arrays.stream(UserDTO.class.getDeclaredFields())
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

            idOfUserToEdit = user.get().getId();
            userToEdit = SignupEditUtil.createUserDTOFromUserLogin(user.get());

            dataMap = SignupEditUtil.addCitiesAndProfessions(dataMap, userToEdit.getRole(), professionsDAO);
            dataMap.put("roles", Arrays.stream(Role.values()).collect(Collectors.toList()));
            dataMap.put("inputData", userToEdit);
            dataMap.put("errors", mapOfErrors);
        }

        FreemarkerUtil.processData(resp, template, dataMap, logger);

    }



    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDTO modifiedUser = null;

        try {
            modifiedUser = SignupEditUtil.createUserDTOToValidate(req, "edit");
        } catch (FrontEndFormValidationException e){
            resp.setStatus(500);
        }


        Set<ConstraintViolation<UserDTO>> constraintViolations =
                SignupEditUtil.validateUserDTO(modifiedUser);


        for (ConstraintViolation<UserDTO> constraintViolation: constraintViolations){
            mapOfErrors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
        }

        dataMap.put("errors", mapOfErrors);
        dataMap.put("inputData", modifiedUser);


        UserLogin us = SignupEditUtil.createUserLoginFromUserDTO(modifiedUser);


        if (constraintViolations.isEmpty()){
                userLoginDAO.save(SignupEditUtil.createUserLoginFromUserDTO(modifiedUser));
                resp.sendRedirect("/login-form");

        } else {

            FreemarkerUtil.processData(resp, template, dataMap, logger);

        }




    }
}
