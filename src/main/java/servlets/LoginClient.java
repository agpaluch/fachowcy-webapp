package servlets;

import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.CityDistrict;
import repository.TypeOfProfession;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet("/login-client")
public class LoginClient extends HttpServlet {

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
        PrintWriter printWriter = resp.getWriter();

        Map<String, Object> map = new HashMap<>();
        map.put("content", "login-client");
        map.put("sessionInfo", sessionInfo);

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter("username");
        String password=req.getParameter("password");
/*        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();*/

        resp.sendRedirect("/details-client");
/*
        RequestDispatcher rd = req.getRequestDispatcher("details-client.ftlh");
        rd.forward(req, resp);
*/

    }
}