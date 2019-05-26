package servlets;

import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.RepositoryOfUsers;
import session.SessionInfo;

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

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;


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

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("content", "login-form");
        dataMap.put("sessionInfo", sessionInfo);


        if (error==null){
            dataMap.put("error","");
        } else {
            dataMap.put("error",error);
        }

        try {
            template.process(dataMap, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RepositoryOfUsers.fillDatabase();

        String email = request.getParameter("email");
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