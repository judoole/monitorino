package com.github.judoole.healthcheck;

import com.github.judoole.healthcheck.cases.HealthcheckThatFailes;
import com.github.judoole.healthcheck.cases.HealthcheckThatIsSuccess;
import com.github.judoole.healthcheck.cases.HealthcheckThatThrowsRuntimeException;
import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.dto.HealthcheckProperty;
import com.github.judoole.healthcheck.properties.JavaVersionHealthcheckProperty;
import com.github.judoole.healthcheck.web.HealthcheckController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public HealthcheckProperty mySimpleProperty() {
        return new HealthcheckProperty("My Simple property", "My simple value");
    }

    @Bean
    public JavaVersionHealthcheckProperty javaVersionhealthcheckProperty() {
        return new JavaVersionHealthcheckProperty();
    }

    @Bean
    public HealthcheckController healthcheckController() {
        return new HealthcheckController();
    }

//    @Bean
//    public HealthcheckController healthcheckControllerMyOwnRequestMapping() {
//        return new HealthcheckControllerMyOwnRequestMapping();
//    }
}
