package com.github.judoole.monitorino;

import com.github.judoole.monitorino.cases.AssertThatFailes;
import com.github.judoole.monitorino.cases.AssertThatThrowsRuntimeException;
import com.github.judoole.monitorino.cases.AssertTwoPlusTwoIsFour;
import com.github.judoole.monitorino.cases.EverythingIsOk;
import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.web.MonitorinoController;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SpringApplicationContext {

    @Bean
    public MonitorinoRunner caseOfTwoPlusTwo() {
        return new AssertTwoPlusTwoIsFour();
    }

    @Bean
    public MonitorinoRunner everythingIsOk() {
        return new EverythingIsOk();
    }

    @Bean
    public MonitorinoRunner assertThatFailes() {
        return new AssertThatFailes();
    }

    @Bean
    public MonitorinoRunner assertThatThrowsRuntimeException() {
        return new AssertThatThrowsRuntimeException();
    }

    @Bean
    public MonitorinoController monitorinoController() throws IOException {
        MonitorinoController controller = new MonitorinoController();
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
    public MonitorinoController controllerWithMyOwnMapping() {
        ControllerWithMyOwnRequestMapping controller = new ControllerWithMyOwnRequestMapping();
        controller.setName("App Smoketest");
        return controller;
    }
}
