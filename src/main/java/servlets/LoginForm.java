package servlets;

import dao.ClientLogin;
import dao.User;
import dao.UserCRUDDao;
import dao.UserLogin;
import exceptions.NoSuchUserException;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.RepositoryOfUsers;
import session.SessionInfo;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login-form")
public class LoginForm extends HttpServlet {

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfoBean sessionInfo;

/*    @Inject
    private TemplateProvider templateProvider;*/

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

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

        resp.setContentType("text/html; charset=utf-8");
        String error= req.getParameter("error");

        Map<String, Object> map = new HashMap<>();
        map.put("content", "login-form");
        /*Map<String, String> map = new HashMap<>();
        map.put("", "");
*/
        map.put("error",error);
        map.put("content", "login-client");
        try {
            template.process(map, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userType = sessionInfo.getUserType();

        UserCRUDDao database = null;

        if(userType.equals("professional")) {
            database = RepositoryOfUsers.getProfessionalsDatabaseDaoBean();
        } else {
            database = RepositoryOfUsers.getClientsDatabaseDaoBean();
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        RepositoryOfUsers.fillDatabase();
        for (Map.Entry<String, ClientLogin> entry : RepositoryOfUsers.getClientsDatabaseDaoBean().getLogin().entrySet()) {

            if (entry.getValue().getEmail().equals(login) && entry.getValue().getPassword().equals(password)) {
                String keyForClientDetails = entry.getKey();
                sessionInfo.setClientLogin(entry.getValue());
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/login-form?error=1");
            }
        }

        /*String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = usersRepositoryDao.getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            String name = user.getName();
            sessionInfo.setLogin(login);
            sessionInfo.setName(name);
            response.sendRedirect("/hw/index");
        } else {
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("content", "login");
            dataModel.put("auth", "fail");
            Template template = templateProvider.getTemplate(
                    getServletContext(), TEMPLATE_NAME
            );
            try {
                template.process(dataModel, response.getWriter());
            } catch (TemplateException e) {
                System.err.println("Error while processing template: " + e);
            }
        }*/
    }

}