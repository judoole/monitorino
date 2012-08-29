package com.github.judoole.monitorino.internal;

import com.github.judoole.monitorino.internal.dto.Stacktrace;
import com.github.judoole.monitorino.internal.dto.TestCase;
import com.github.judoole.monitorino.internal.dto.TestSuite;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Collection;
import java.util.Properties;

public class HealthcheckSuiteAssembler {
    private final Collection<HealthcheckCaseRunner> healthcheckCaseRunners;
    private final String suiteName;
    private Properties healthcheckProperties;

    public HealthcheckSuiteAssembler(String healthcheckName, Collection<HealthcheckCaseRunner> healthcheckCaseRunners, Properties healthcheckProperties) {
        this.healthcheckCaseRunners = healthcheckCaseRunners;
        this.suiteName = healthcheckName;
        this.healthcheckProperties = healthcheckProperties;
    }

    public TestSuite run() {
        TestSuite suite = new TestSuite();
        suite.name = suiteName;
        suite.healthcheckProperties = healthcheckProperties;
        StopWatch stopWatch = new StopWatch().start();
        for (HealthcheckCaseRunner runner : healthcheckCaseRunners) {
            runCase(suite, runner);
        }
        suite.time = stopWatch.stop().timeInSeconds().toString();
        return suite;
    }

    private void runCase(TestSuite suite, HealthcheckCaseRunner runner) {
        try {
            suite.addCase(runner.run());
        } catch (Exception e) {
            createCaseWithErrorFromExceptionAndAdd(suite, e);
        }
    }

    private void createCaseWithErrorFromExceptionAndAdd(TestSuite suite, Throwable e) {
        TestCase testCase = new TestCase();
        testCase.name = e.getClass().getName();
        Stacktrace error = new Stacktrace();
        error.message = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        testCase.error = error;
        suite.addCase(testCase);
    }
}