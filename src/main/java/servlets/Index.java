package servlets;

import dao.UserLoginDAO;
import domain.Role;
import domain.UserDetails;
import domain.UserLogin;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.City;
import repository.TypeOfProfession;
import session.SessionInfo;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/")
public class Index extends HttpServlet {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @Override
    public void init() {

        try {
            template = TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        UserDetails userDetails = UserDetails.builder()
                //.userLogin(userLogin)
                .name("aaa")
                .surname("bbb")
                .profession(TypeOfProfession.ELECTRICIAN)
                .phoneNumber(600000000)
                .city(City.WARSZAWA)
                .longitude(6.6)
                .latitude(7.7)
                .build();
        UserLogin userLogin = UserLogin.builder()
                .userDetails(userDetails)
                .email("fuckme@abc.xom")
                .password("password")
                .role(Role.ADMIN)
                .build();
        //userLogin.setPassword("Password");
        userLoginDAO.save(userLogin);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, Object> map = new HashMap<>();
        map.put("content", "index");
        map.put("sessionInfo", sessionInfo);


        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
