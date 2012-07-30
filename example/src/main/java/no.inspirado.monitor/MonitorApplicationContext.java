package no.inspirado.monitor;

import no.inspirado.monitor.cases.MonitorCaseThatFailes;
import no.inspirado.monitor.cases.MonitorCaseThatIsSuccess;
import no.inspirado.monitor.cases.MonitorCaseThatThrowsRuntimeException;
import no.inspirado.monitor.internal.MonitorCaseRunner;
import no.inspirado.monitor.internal.dto.MonitorProperty;
import no.inspirado.monitor.web.MonitorController;
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
    public MonitorController monitorController(){
        return new MonitorController();
    }

    @Bean
    public MonitorController monitorControllerMyOwnRequestMapping(){
        return new MonitorControllerMyOwnRequestMapping();
    }
}
