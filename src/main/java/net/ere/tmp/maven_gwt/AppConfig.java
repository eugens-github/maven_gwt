package net.ere.tmp.maven_gwt;

import net.ere.tmp.maven_gwt.spring.DataService;
import net.ere.tmp.maven_gwt.spring.TimeService;
import net.ere.tmp.maven_gwt.spring.impl.DataServiceImpl;
import net.ere.tmp.maven_gwt.spring.impl.TimeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAsync
@EnableTransactionManagement
public class AppConfig {

    private final DB usedDB = DB.H2;

    @Bean
    public TimeService timeService() {
        return new TimeServiceImpl();
    }

    @Bean
    public DataService dataService() {
        return new DataServiceImpl();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"net.ere.tmp.maven_gwt.spring.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        if (usedDB == DB.Derby) {
            dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
            dataSource.setUrl("jdbc:derby://localhost:1527/maven_gwt_db;create=true");

            dataSource.setUsername("app");
            dataSource.setPassword("app");
        } else {
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setUrl("jdbc:h2:~/db/h2/maven_gwt_db");

            dataSource.setUsername("sa");
            dataSource.setPassword("sa");
        }

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");

        if (usedDB == DB.Derby) {
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        } else {
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        }

        return properties;
    }

    enum DB {Derby, H2}
}