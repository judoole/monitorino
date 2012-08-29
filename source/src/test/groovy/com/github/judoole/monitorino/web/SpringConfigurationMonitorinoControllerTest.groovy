package com.github.judoole.monitorino.web

import com.github.judoole.monitorino.internal.MonitorinoRunner
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
public class SpringConfigurationMonitorinoControllerTest {

    @Bean
    public MonitorinoRunner runnerToBeInjected() {
        return new MonitorinoRunner() {
            @Override
            protected String getName() {
                return null  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected MonitorinoFailureCase assertNoFailure() {
                return null  //To change body of implemented methods use File | Settings | File Templates.
            }

        };
    }

    @Bean
    public MonitorinoController controller() {
        return new MonitorinoController();
    }
}
