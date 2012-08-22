package com.github.judoole.healthcheck;

import com.github.judoole.healthcheck.cases.AssertTwoPlusTwoIsFour;
import com.github.judoole.healthcheck.cases.EverythingIsOk;
import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.web.HealthcheckController;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SpringApplicationContext {

    @Bean
    public HealthcheckCaseRunner caseOfTwoPlusTwo() {
        return new AssertTwoPlusTwoIsFour();
    }

    @Bean
    public HealthcheckCaseRunner everythingIsOk() {
        return new EverythingIsOk();
    }

    @Bean
    public HealthcheckController healthcheckController() throws IOException {
        HealthcheckController controller = new HealthcheckController();
        controller.setProperties(mavenBuildProperties());
        return controller;
    }

    @Bean
    public Properties mavenBuildProperties() throws IOException {
        PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocation(new ClassPathResource("mavenbuild.properties"));

        Properties extraProps = new Properties();
        extraProps.put("os.name", System.getProperty("os.name"));
        factory.setProperties(extraProps);

        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public HealthcheckController controllerWithMyOwnMapping() {
        ControllerWithMyOwnRequestMapping controller = new ControllerWithMyOwnRequestMapping();
        controller.setName("App Smoketest");
        return controller;
    }
}