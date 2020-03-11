package servlets;

import dao.ProfessionsDAO;
import dao.UserLoginDAO;
import domain.Professions;
import session.SessionInfo;
import template.TemplateProvider;
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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/index")
public class Index extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @EJB
    ProfessionsDAO professionsDAO;


    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));
        //ContactServer.runContactService();
        //ContactClient.testClient();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        List<Professions> list = professionsDAO.getAll();



        Map<String, Object> map = new HashMap<>();
        map.put("content", "index");
        map.put("sessionInfo", sessionInfo);
        map.put("professionals", userLoginDAO.getAllProfessionals());
        map.put("professions", professionsDAO.getAll());

        templateProxy.freemarkerEngine(map, resp);



    }

}
