package template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;

public class TemplateProvider {

    private static final Logger logger = LoggerFactory.getLogger(TemplateProvider.class);
    public static final String TEMPLATES_DIRECTORY_PATH = "/fm-templates";
    private static final String TEMPLATE_EXT = ".ftlh";

    public static Template createTemplate(ServletContext servletContext, String templateName) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setServletContextForTemplateLoading(servletContext, TEMPLATES_DIRECTORY_PATH);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);

        try {
            return configuration.getTemplate(templateName + TEMPLATE_EXT);
        } catch (IOException e){
            logger.error("Could not create template: "+templateName, e);
            throw new IllegalStateException(e);
        }

    }
}
