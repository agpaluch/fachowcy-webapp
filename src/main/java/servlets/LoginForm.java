package servlets;

import exceptions.NoSuchUserException;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import session.SessionInfo;

import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("content", "login-form");
        dataMap.put("sessionInfo", sessionInfo);
        dataMap.put("errorMessage", "");

        try {
            template.process(dataMap, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        Map <String, Object> dataMap = new HashMap<>();
        dataMap.put("content", "login-form");
        dataMap.put("sessionInfo", sessionInfo);
        dataMap.put("errorMessage", "");


        if (sessionInfo.isAuthorized(email, password)){
            try {
                sessionInfo.findUserLoginByEmail(email);
                sessionInfo.findUserDetailsByEmail(email);
                if (sessionInfo.getUserType().equals("professional")){
                    response.sendRedirect("/details-prof");
                } else if (sessionInfo.getUserType().equals("client")){
                    response.sendRedirect("/details-client");
                }

            } catch (NoSuchUserException e){
                dataMap.put("errorMessage", "Podanego adresu e-mail nie ma w bazie.");
            }
        } else {
            dataMap.put("errorMessage", "Nieprawidłowy adres e-mail lub hasło.");
        }


        try {
                template.process(dataMap, response.getWriter());
            } catch (TemplateException e) {
                System.err.println("Error while processing template: " + e);
            }
        }


}