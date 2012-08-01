package com.github.judoole.healthcheck.web

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner
import org.springframework.context.annotation.Bean

import org.springframework.context.annotation.Configuration
import com.github.judoole.healthcheck.internal.dto.HealthcheckProperty
import com.github.judoole.healthcheck.internal.dto.HealthcheckFailureCase

@Configuration
public class SpringConfigurationHealthcheckControllerTest {

    @Bean
    public HealthcheckCaseRunner runnerToBeInjected() {
        return new HealthcheckCaseRunner() {
            @Override
            protected String getName() {
                return null  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected HealthcheckFailureCase assertNoFailure() {
                return null  //To change body of implemented methods use File | Settings | File Templates.
            }

        };
    }

    @Bean
    public HealthcheckProperty myPropertyToBeInjected(){
        return new HealthcheckProperty("My Property Name", "My property value")
    }

    @Bean
    public HealthcheckController controller() {
        return new HealthcheckController();
    }
}
