package no.inspirado.monitor.web

import no.inspirado.monitor.internal.MonitorCaseRunner
import org.springframework.context.annotation.Bean
import no.inspirado.monitor.internal.dto.MonitorFailureCase
import org.springframework.context.annotation.Configuration
import no.inspirado.monitor.internal.dto.MonitorProperty

@Configuration
public class SpringConfigurationMonitorControllerTest {

    @Bean
    public MonitorCaseRunner runnerToBeInjected() {
        return new MonitorCaseRunner() {
            @Override
            protected String getName() {
                return null  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected MonitorFailureCase assertNoFailure() {
                return null  //To change body of implemented methods use File | Settings | File Templates.
            }

        };
    }

    @Bean
    public MonitorProperty myPropertyToBeInjected(){
        return new MonitorProperty("My Property Name", "My property value")
    }

    @Bean
    public MonitorController controller() {
        return new MonitorController();
    }
}
