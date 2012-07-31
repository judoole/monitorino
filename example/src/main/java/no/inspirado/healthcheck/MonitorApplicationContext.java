package no.inspirado.healthcheck;

import no.inspirado.healthcheck.cases.MonitorCaseThatFailes;
import no.inspirado.healthcheck.cases.MonitorCaseThatIsSuccess;
import no.inspirado.healthcheck.cases.MonitorCaseThatThrowsRuntimeException;
import no.inspirado.healthcheck.internal.MonitorCaseRunner;
import no.inspirado.healthcheck.internal.dto.MonitorProperty;
import no.inspirado.healthcheck.properties.JavaVersionMonitorProperty;
import no.inspirado.healthcheck.web.MonitorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorApplicationContext {

    @Bean
    public MonitorCaseRunner monitorCaseThatFailes() {
        return new MonitorCaseThatFailes();
    }

    @Bean
    public MonitorCaseRunner monitorCaseThatIsSuccess() {
        return new MonitorCaseThatIsSuccess();
    }

    @Bean
    public MonitorCaseRunner monitorCaseThatThrowsRuntimeException() {
        return new MonitorCaseThatThrowsRuntimeException();
    }

    @Bean
    public MonitorProperty mySimpleProperty(){
        return new MonitorProperty("My Simple property", "My simple value");
    }

    @Bean
    public JavaVersionMonitorProperty javaVersionMonitorProperty(){
        return new JavaVersionMonitorProperty();
    }

    @Bean
    public MonitorController monitorController(){
        return new MonitorController();
    }

    @Bean
    public MonitorController monitorControllerMyOwnRequestMapping(){
        return new MonitorControllerMyOwnRequestMapping();
    }
}
