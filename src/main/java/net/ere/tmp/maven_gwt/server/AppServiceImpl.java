package net.ere.tmp.maven_gwt.server;

import java.util.Date;
import java.util.logging.Logger;

import net.ere.tmp.maven_gwt.shared.AppService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.ere.tmp.maven_gwt.spring.TimeService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;

public class AppServiceImpl extends RemoteServiceServlet implements AppService {

    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getLogger(AppServiceImpl.class.getName());

    private TimeService timeService;

    private <T> T getBean(java.lang.Class<T> aClass) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        return ctx.getBean(aClass);
    }

    @Override
    public Date getTime() {
        TimeService timeService = getBean(TimeService.class);

        Date time = timeService.getTime();

        log.info("Call getTime(): " + timeService.getTimeAsString());
        return time;
    }

    @Override
    public String getPropertyValue(String propertyKey) {
        log.info("Call getPropertyValue(" + propertyKey + ")");
        return System.getProperty(propertyKey);
    }

}
