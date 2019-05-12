package servlets;

import dao.ClientLogin;
import dao.UserCRUDDao;
import dao.UserLogin;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.RepositoryOfUsers;
import session.SessionInfo;
import session.SessionInfoBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login-form")
public class LoginForm extends HttpServlet {

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

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


        if (error==null){
            map.put("error","");
        } else {
            map.put("error",error);
        }

        try {
            template.process(map, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RepositoryOfUsers.fillDatabase();


        String email = request.getParameter("login");
        String password = request.getParameter("password");

        sessionInfo.setPassword(password);
        sessionInfo.setEmail(email);

        if(sessionInfo.findUserByEmailAndPassword()){
            response.sendRedirect("/");
        }
        else {
            response.sendRedirect("/login-form?error=1");
        }


    }

}