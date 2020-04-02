package servlets;

import template.TemplateProvider;
import dao.CommentDAO;
import dao.MessagesDAO;
import dao.ProfessionsDAO;
import dao.UserLoginDAO;
import domain.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.City;
import session.SessionInfo;
import template.TemplateProxy;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet("/details-prof")
public class DetailsProf extends HttpServlet {

    private Map<String, String> mapOfErrors = new HashMap<>();
    private Map<String, Object> mapOfValues = new HashMap<>();

    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @EJB
    ProfessionsDAO professionsDAO;

    @EJB
    MessagesDAO messagesDAO;

    @EJB
    CommentDAO commentDAO;

    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        mapOfValues = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        mapOfErrors = Arrays.stream(UserDTO.class.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        Map<String, Object> map = new HashMap<>();

        Optional<UserLogin> maybeUser = userLoginDAO.getByLogin(sessionInfo.getEmail());

        maybeUser.ifPresent(userLogin -> map.put("userLogin", userLogin));

        map.put("content", "details-prof");
        map.put("sessionInfo", sessionInfo);
        map.put("errors", mapOfErrors);
        map.put("inputData", mapOfValues);
        map.put("cities", Arrays.stream(City.values()).collect(Collectors.toList()));

        if(sessionInfo.getRole().equals(Role.PROFESSIONAL)) {
            map.put("professions", professionsDAO.getAll());
        }

        templateProxy.freemarkerEngine(map, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("delete") != null) {

            long idToDelete = userLoginDAO.getIDbyLogin(sessionInfo.getEmail()).get();
            messagesDAO.clearDeletedUser(idToDelete);
            commentDAO.clearDeletedUser(idToDelete);

            userLoginDAO.deleteByLogin(sessionInfo.getEmail());
            resp.sendRedirect("/logout");
        } else {

            Professions profession = null;
            if (sessionInfo.getRole().equals(Role.PROFESSIONAL)) {
                profession = professionsDAO.getByProfession(req.getParameter("profession")).get();
            }

            String login = req.getParameter("email");

            UserDetails userDetails = UserDetails.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .latitude(Double.valueOf(req.getParameter("latitude")))
                    .longitude(Double.valueOf(req.getParameter("longitude")))
                    .phoneNumber(Long.parseLong(req.getParameter("phoneNumber")))
                    .city(Enum.valueOf(City.class, req.getParameter("city")))
                    .numberOfLikes(userLoginDAO.getDetailsByLogin(login).get().getNumberOfLikes())
                    .build();

            UserLogin userLogin = UserLogin.builder()
                    .email(login)
                    .password(req.getParameter("password"))
                    .role(userLoginDAO.getRoleByLogin(login).get())
                    .userDetails(userDetails)
                    .profession(profession)
                    .build();

            userLoginDAO.save(userLogin);
            resp.sendRedirect("/fachmann");
        }
    }
}