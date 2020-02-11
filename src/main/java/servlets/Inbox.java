package servlets;

import template.TemplateProvider;
import dao.MessagesDAO;
import dao.UserLoginDAO;
import domain.Messages;
import domain.UserLogin;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import session.SessionInfo;

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

@WebServlet("/inbox")
public class Inbox extends HttpServlet {


    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;

    @EJB
    MessagesDAO messages;

    @Override
    public void init() {
        try {
            template = TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, Object> map = new HashMap<>();
        map.put("content", "inbox");
        map.put("sessionInfo", sessionInfo);

        Optional<UserLogin> user = userLoginDAO.getByLogin(sessionInfo.getEmail());
        if (user.isPresent()) {
            long id = user.get().getId();
            if (messages.getByRecipient(id).isPresent()) {
                map.put("messages", messages.getByRecipient(id).get());
                for(Messages message : messages.getByRecipient(id).get()) {
                    messages.setToRead(message.getId());
                }
            } else {
                map.put("messages", null);
            }
        }

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
