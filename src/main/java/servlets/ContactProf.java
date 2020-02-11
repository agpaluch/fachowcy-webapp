package servlets;


import dao.ProfessionsDAO;
import dao.UserLoginDAO;
import domain.Professions;
import domain.UserLogin;
import freemarker.template.Template;
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
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet("/contact-prof")
public class ContactProf extends HttpServlet {

    private TemplateProxy templateProxy;
    Map<String, Object> map = new HashMap<>();

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

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

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String userToDisplay = req.getParameterValues("email")[0];
        Optional<UserLogin> maybeUser = userLoginDAO.getByLogin(userToDisplay);
        Optional<Professions> maybeProfession = professionsDAO.getProfessionByLogin(userToDisplay);

        map.put("userLogin", maybeUser.get());
        map.put("userEmail", userToDisplay);
        map.put("content", "contact-prof");
        map.put("sessionInfo", sessionInfo);
        //map.put("profession", maybeProfession.get().getProfession());

        templateProxy.freemarkerEngine(map, resp);


    }
}
