package servlets;

import dao.Role;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import session.SessionInfo;

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

        Map<String, Object> map = new HashMap<>();
        map.put("content", "login");
        map.put("sessionInfo", sessionInfo);

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
            sessionInfo.setUserType(Role.CLIENT);
        }
        if(isProf != null){
            sessionInfo.setUserType(Role.PROFESSIONAL);
        }


        response.sendRedirect("/login-form");

    }

}