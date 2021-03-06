package template;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class TemplateProxy {

    private static final Logger logger = LoggerFactory.getLogger(TemplateProxy.class);
    private final Template template;

    public void freemarkerEngine(Map<String, Object> dataMap, HttpServletResponse resp) {

        try {
            this.template.process(dataMap, resp.getWriter());
        } catch (TemplateException | IOException e) {
            logger.error("Could not process template", e);
            throw new IllegalArgumentException(e);
        }

    }



}
