/**
 * To change this license header, choose License Headers in Project Properties.* To change this template file, choose Tools | Templates* and open the template in the editor.
 */package crdhn.confkeeper.template;

import firo.ModelAndView;
import java.io.IOException;
import java.io.StringWriter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import firo.TemplateEngine;
import java.util.Map;

/**
 * ** @author hoaronal
 */
public class RenderEngine extends TemplateEngine {

    private Configuration configuration;
    private static RenderEngine _instance;

    public static RenderEngine getInstance() {
        if (_instance == null) {
            _instance = new RenderEngine();
        }
        return _instance;
    }

    public RenderEngine() {
        this.configuration = createDefaultConfiguration();
    }

    public RenderEngine(Configuration configuration) {
        this.configuration = configuration;
    }

    public String render(Map attributes, String tplPath) {
        try {
            StringWriter stringWriter = new StringWriter();
            Template template = configuration.getTemplate(tplPath);
            template.process(attributes, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private Configuration createDefaultConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(RenderEngine.class, "/crdhn/confkeeper/view/");
        return configuration;
    }

    @Override
    public String render(ModelAndView mav) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
