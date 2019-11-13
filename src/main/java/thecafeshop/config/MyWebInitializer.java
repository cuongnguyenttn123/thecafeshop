package thecafeshop.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                PersistenceJPAConfig.class, FileStorageProperties.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                SpringWebConfig.class
        };
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
