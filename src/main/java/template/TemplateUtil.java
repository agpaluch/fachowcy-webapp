package template;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class TemplateUtil {


    static void freemarkerEngine(Map<String, Object> dataMap, Template template, PrintWriter printWriter, Logger logger) {

        try {
            template.process(dataMap, printWriter);
        } catch (TemplateException | IOException e) {
            logger.error(e.getMessage());
        }

    }

    public static Template templateFactory(ServletContext context, String templateName, Logger logger) {
        Template template = null;
        try {
            template = TemplateProvider.createTemplate(context, templateName);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return template;
    }


}
