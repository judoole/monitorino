package com.github.judoole.healthcheck.internal;

import com.github.judoole.healthcheck.internal.dto.HealthcheckCase;
import com.github.judoole.healthcheck.internal.dto.HealthcheckStacktrace;
import com.github.judoole.healthcheck.internal.dto.HealthcheckSuite;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class HealthcheckSuiteAssembler {
    private final Collection<HealthcheckCaseRunner> healthcheckCaseRunners;
    private final String suiteName;
    private Properties healthcheckProperties;

    public HealthcheckSuiteAssembler(String healthcheckName, Collection<HealthcheckCaseRunner> healthcheckCaseRunners, Properties healthcheckProperties) {
        this.healthcheckCaseRunners = healthcheckCaseRunners;
        this.suiteName = healthcheckName;
        this.healthcheckProperties = healthcheckProperties;
    }

    public HealthcheckSuite run() {
        HealthcheckSuite suite = new HealthcheckSuite();
        suite.name = suiteName;
        suite.healthcheckProperties = healthcheckProperties;
        StopWatch stopWatch = new StopWatch().start();
        for (HealthcheckCaseRunner runner : healthcheckCaseRunners) {
            runCase(suite, runner);
        }
        suite.time = stopWatch.stop().timeInSeconds().toString();
        return suite;
    }

    private void runCase(HealthcheckSuite suite, HealthcheckCaseRunner runner) {
        try {
            suite.addCase(runner.run());
        } catch (Exception e) {
            createCaseWithErrorFromExceptionAndAdd(suite, e);
        }
    }

    private void createCaseWithErrorFromExceptionAndAdd(HealthcheckSuite suite, Throwable e) {
        HealthcheckCase healthcheckCase = new HealthcheckCase();
        healthcheckCase.name = e.getClass().getName();
        HealthcheckStacktrace error = new HealthcheckStacktrace();
        error.message = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        healthcheckCase.error = error;
        suite.addCase(healthcheckCase);
    }
}