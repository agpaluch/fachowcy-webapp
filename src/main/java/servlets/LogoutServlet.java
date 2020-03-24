package servlets;

import template.TemplateProvider;
import freemarker.template.Template;
import session.SessionInfo;
import template.TemplateProxy;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        sessionInfo.setEmail(null);
        sessionInfo.setRole(null);
        sessionInfo.setPassword(null);
        sessionInfo.setUserLogin(null);

        resp.sendRedirect("/fachmann");

    }
}