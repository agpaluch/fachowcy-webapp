package servlets;

import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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

@WebServlet("/login")
public class Login extends HttpServlet {

    @Inject
    SessionInfoBean sessionInfo;

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";

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

        Map<String, Object> map = new HashMap<>();
        map.put("content", "login");
        map.put("", "");
        if(sessionInfo.getClientLoginUser()!=null){
            map.put("username",sessionInfo.getClientLoginUser().getEmail());

        }
        if(sessionInfo.getProfessionalLogin()!=null){
            map.put("prof",sessionInfo.getProfessionalLogin().getEmail());
        }
        try {
            template.process(map, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String isProf = request.getParameter("profLogin");
        String isClient = request.getParameter("clientLogin");

        if(isClient != null) {
            sessionInfo.setUserType("client");

        }
        if(isProf != null){
            sessionInfo.setUserType("professional");
        }

        System.out.println(sessionInfo.getUserType());
        response.sendRedirect("/login-form");


    }

}