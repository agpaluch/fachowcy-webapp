package servlets;

import template.TemplateProvider;
import dao.MessagesDAO;
import dao.UserLoginDAO;
import domain.UserLogin;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import session.SessionInfo;
import template.TemplateProxy;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/sent")
public class Sent extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @EJB
    MessagesDAO messages;

    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=utf-8");

        Map<String, Object> map = new HashMap<>();
        map.put("content", "sent");
        map.put("sessionInfo", sessionInfo);

        Optional<UserLogin> user = userLoginDAO.getByLogin(sessionInfo.getEmail());
        if (user.isPresent()) {
            long id = user.get().getId();
            if (messages.getBySender(id).isPresent()) {
                map.put("messages", messages.getBySender(id).get());
            } else {
                map.put("messages", null);
            }
        }


        templateProxy.freemarkerEngine(map, resp);

    }

}
