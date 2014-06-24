package net.ere.tmp.maven_gwt;

import net.ere.tmp.maven_gwt.spring.DataService;
import net.ere.tmp.maven_gwt.spring.TimeService;
import net.ere.tmp.maven_gwt.spring.impl.DataServiceImpl;
import net.ere.tmp.maven_gwt.spring.impl.TimeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TimeService timeService() {
        return new TimeServiceImpl();
    }

    @Bean
    public DataService dataService() {
        return new DataServiceImpl();
    }
}
