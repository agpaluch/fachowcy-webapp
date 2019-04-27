package servlets;

import dao.ClientLogin;
import dao.User;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.CityDistrict;
import repository.RepositoryOfUsers;
import repository.TypeOfProfession;
import session.SessionInfoBean;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static repository.RepositoryOfUsers.fillDatabase;

@WebServlet("/login-form")
public class LoginClient extends HttpServlet {
    @Inject
    SessionInfoBean user;

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";
    User setUserEmail; // creating user object for set user in SessionInfoBean
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
       String error= req.getParameter("error");

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();


        Map<String,String> map = new HashMap<>();

        map.put("error",error);
        map.put("content", "login-client");

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("username");
        String password = req.getParameter("password");
        RepositoryOfUsers.fillDatabase();

        for (Map.Entry<String, ClientLogin> entry : RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().entrySet()) {
            if (entry.getValue().getEmail().equals(login) && entry.getValue().getPassword().equals(password)) {
               String keyForClientDetails = entry.getKey();
                        setUserEmail.setEmail(login);
                        user.setUser(setUserEmail);
                    resp.sendRedirect("/");
            }
            else {
                resp.sendRedirect("/login-form?error=1");
            }
        }
//        resp.setContentType("text/html; charset=utf-8");
//        PrintWriter printWriter = resp.getWriter();
//
//        RequestDispatcher rd = req.getRequestDispatcher("details-client.ftlh");
//        rd.forward(req, resp);

    }

}