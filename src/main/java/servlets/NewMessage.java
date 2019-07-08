package servlets;

import config.TemplateProvider;
import dao.MessagesDAO;
import dao.UserLoginDAO;
import domain.Messages;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/newmessage")
public class NewMessage extends HttpServlet {


    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";
    private long recipientId;

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
        map.put("content", "newmessage");
        map.put("sessionInfo", sessionInfo);

        recipientId = Integer.parseInt(req.getParameter("id"));
        if (userLoginDAO.get(recipientId).isPresent()) {
            map.put("recipient", userLoginDAO.get(recipientId).get());
        }

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, Object> map = new HashMap<>();
        map.put("content", "newmessage");
        map.put("sessionInfo", sessionInfo);


        String newMessageText = req.getParameter("message");
        if (newMessageText.length() > 0 && newMessageText.length() <= 400
                && userLoginDAO.getByLogin(sessionInfo.getEmail()).isPresent()
                && userLoginDAO.get(recipientId).isPresent()) {
            Messages message = new Messages();
            message.setSender(userLoginDAO.getByLogin(sessionInfo.getEmail()).get());
            message.setRecipient(userLoginDAO.get(recipientId).get());
            message.setMessage(newMessageText);
            messages.save(message);
            map.put("recipient", userLoginDAO.get(recipientId).get());
            resp.sendRedirect("/sent");
        }

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
