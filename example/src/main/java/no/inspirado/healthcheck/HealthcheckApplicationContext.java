package no.inspirado.healthcheck;

import no.inspirado.healthcheck.cases.HealthcheckThatFailes;
import no.inspirado.healthcheck.cases.HealthcheckThatIsSuccess;
import no.inspirado.healthcheck.cases.HealthcheckThatThrowsRuntimeException;
import no.inspirado.healthcheck.internal.HealthcheckCaseRunner;
import no.inspirado.healthcheck.internal.dto.HealthcheckProperty;
import no.inspirado.healthcheck.properties.JavaVersionHealthcheckProperty;
import no.inspirado.healthcheck.web.HealthcheckController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthcheckApplicationContext {

    @Bean
    public HealthcheckCaseRunner monitorCaseThatFailes() {
        return new HealthcheckThatFailes();
    }

    @Bean
    public HealthcheckCaseRunner monitorCaseThatIsSuccess() {
        return new HealthcheckThatIsSuccess();
    }

    @Bean
    public HealthcheckCaseRunner monitorCaseThatThrowsRuntimeException() {
        return new HealthcheckThatThrowsRuntimeException();
    }

    @Bean
    public HealthcheckProperty mySimpleProperty() {
        return new HealthcheckProperty("My Simple property", "My simple value");
    }

    @Bean
    public JavaVersionHealthcheckProperty javaVersionMonitorProperty() {
        return new JavaVersionHealthcheckProperty();
    }

    @Bean
    public HealthcheckController monitorController() {
        return new HealthcheckController();
    }

    @Bean
    public HealthcheckController monitorControllerMyOwnRequestMapping() {
        return new HealthcheckControllerMyOwnRequestMapping();
    }
}
