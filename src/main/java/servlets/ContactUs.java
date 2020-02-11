package servlets;

import session.SessionInfo;
import template.TemplateProvider;
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
import java.util.logging.Logger;

@WebServlet("/contact-us")
public class ContactUs extends HttpServlet {

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
        dataMap.put("content", "contact-us");
        dataMap.put("sessionInfo", sessionInfo);

        templateProxy.freemarkerEngine(dataMap, resp);


/*        URL url = new URL("http://172.17.0.3:9999/service?wsdl");
        QName qName = new QName("http://contactService.webapp.isa/", "ContactServiceImpService");
        Service service = Service.create(url, qName);
        ContactService port = service.getPort(ContactService.class);
        System.out.println(port.toUpperCase("ala"));

        ContactEvent contactEvent = ContactEvent.builder()
                .email("client2@gmail.com")
                .reason("gtfo")
                .message("buka mumin")
                .build();

        port.addContactEvent(contactEvent);*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/index");

    }
}
