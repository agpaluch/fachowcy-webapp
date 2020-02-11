package servlets;

import template.TemplateProvider;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/admin/stats")
public class AdminStats extends HttpServlet {

    private static final String TEMPLATE_NAME = "index";
    Logger logger = Logger.getLogger(getClass().getName());
    private TemplateProxy templateProxy;

    @Inject
    SessionInfo sessionInfo;

    @Override
    public void init() {
        templateProxy = new TemplateProxy(TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("content", "adminStats");
        dataMap.put("sessionInfo", sessionInfo);

        templateProxy.freemarkerEngine(dataMap, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
