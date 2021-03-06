package servlets;

import template.TemplateProvider;
import dao.UserLoginDAO;
import domain.UserLogin;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import session.SessionInfo;
import template.TemplateProxy;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/search-results")
public class SearchResults extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @Inject
    UserLoginDAO userLoginDAO;
/*    @EJB(beanName = "UserDaoBean")
    UserDao professionalDao;*/


    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        Map<String, Object> map = new HashMap<>();
        map.put("content", "search-results");
        map.put("sessionInfo", sessionInfo);

        templateProxy.freemarkerEngine(map, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        Map<String, Object> map = new HashMap<>();
        map.put("content", "search-results");
        map.put("sessionInfo", sessionInfo);
        //map.put("se", req.getParameter("search"));
        String searchQuery = req.getParameter("search");

        List<UserLogin> mayBeProfessionals = userLoginDAO.getProfByProfession(searchQuery);
        map.put("searchResults", mayBeProfessionals);

        templateProxy.freemarkerEngine(map, resp);

    }
}