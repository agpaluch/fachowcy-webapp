package servlets;



import template.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FreemarkerUtil {

    public static Template createTemplate(String templateName, Logger logger, ServletContext servletContext){

        try {
            return TemplateProvider.createTemplate(servletContext, templateName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public static void processData(HttpServletResponse resp, Template template, Map dataMap, Logger logger){

        resp.setContentType("text/html; charset=utf-8");

        try {
            PrintWriter printWriter = resp.getWriter();
            template.process(dataMap, printWriter);
        } catch (TemplateException | IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
