package servlets;

import dao.UserLoginDAO;
import template.TemplateProvider;
import domain.Role;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login-form")
public class LoginForm extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;



    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));

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

        templateProxy.freemarkerEngine(dataMap, resp);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(sessionInfo.validateUser(email, password)) {

            Role role = userLoginDAO.getRoleByLogin(email).get();
            sessionInfo.setRole(role);
            sessionInfo.setPassword(password);
            sessionInfo.setEmail(email);
            sessionInfo.setUserLogin(userLoginDAO.getByLogin(email).get());

            response.sendRedirect("/index");

        }
        else {
            response.sendRedirect("/login-form?error=1");
        }


    }



}