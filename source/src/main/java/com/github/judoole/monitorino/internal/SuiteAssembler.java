package com.github.judoole.monitorino.internal;

import com.github.judoole.monitorino.internal.dto.Case;
import com.github.judoole.monitorino.internal.dto.Stacktrace;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Collection;
import java.util.Properties;

public class SuiteAssembler {
    private final Collection<MonitorinoRunner> runners;
    private final String suiteName;
    private Properties healthcheckProperties;

    public SuiteAssembler(String healthcheckName, Collection<MonitorinoRunner> runners, Properties healthcheckProperties) {
        this.runners = runners;
        this.suiteName = healthcheckName;
        this.healthcheckProperties = healthcheckProperties;
    }

    public MonitorinoSuite run() {
        MonitorinoSuite suite = new MonitorinoSuite();
        suite.name = suiteName;
        suite.healthcheckProperties = healthcheckProperties;
        StopWatch stopWatch = new StopWatch().start();
        for (MonitorinoRunner runner : runners) {
            runCase(suite, runner);
        }
        suite.time = stopWatch.stop().timeInSeconds().toString();
        return suite;
    }

    private void runCase(MonitorinoSuite suite, MonitorinoRunner runner) {
        try {
            suite.addCase(runner.run());
        } catch (Exception e) {
            createCaseWithErrorFromExceptionAndAdd(suite, e);
        }
    }

    private void createCaseWithErrorFromExceptionAndAdd(MonitorinoSuite suite, Throwable e) {
        Case testCase = new Case();
        testCase.name = e.getClass().getName();
        Stacktrace error = new Stacktrace();
        error.message = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        testCase.error = error;
        suite.addCase(testCase);
    }
}