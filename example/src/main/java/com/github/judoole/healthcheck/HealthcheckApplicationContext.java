package com.github.judoole.healthcheck;

import com.github.judoole.healthcheck.cases.HealthcheckThatFailes;
import com.github.judoole.healthcheck.cases.HealthcheckThatIsSuccess;
import com.github.judoole.healthcheck.cases.HealthcheckThatThrowsRuntimeException;
import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.web.HealthcheckController;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class HealthcheckApplicationContext {

    @Bean
    public HealthcheckCaseRunner healthcheckCaseThatFailes() {
        return new HealthcheckThatFailes();
    }

    @Bean
    public HealthcheckCaseRunner healthcheckCaseThatIsSuccess() {
        return new HealthcheckThatIsSuccess();
    }

    @Bean
    public HealthcheckCaseRunner healthcheckCaseThatThrowsRuntimeException() {
        return new HealthcheckThatThrowsRuntimeException();
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
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public HealthcheckController healthcheckControllerMyOwnRequestMapping() {
        return new HealthcheckControllerMyOwnRequestMapping();
    }
}
