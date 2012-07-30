package no.inspirado.monitor.internal;

import no.inspirado.monitor.internal.dto.MonitorCase;
import no.inspirado.monitor.internal.dto.MonitorProperty;
import no.inspirado.monitor.internal.dto.MonitorStacktrace;
import no.inspirado.monitor.internal.dto.MonitorSuite;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MonitorSuiteAssembler {
    private final Collection<MonitorCaseRunner> monitorCaseRunners;
    private final String suiteName;
    private Set<MonitorProperty> monitorProperties;

    public MonitorSuiteAssembler(String monitorName, Collection<MonitorCaseRunner> monitorCaseRunners, Set<MonitorProperty> monitorProperties) {
        this.monitorCaseRunners = monitorCaseRunners;
        this.suiteName = monitorName;
        this.monitorProperties = monitorProperties;
    }

    public MonitorSuite run() {
        MonitorSuite suite = new MonitorSuite();
        suite.name = suiteName;
        suite.properties = monitorProperties;
        StopWatch stopWatch = new StopWatch().start();
        for (MonitorCaseRunner runner : monitorCaseRunners) {
            runCase(suite, runner);
        }
        suite.time = stopWatch.stop().timeInSeconds().toString();
        return suite;
    }

    private void runCase(MonitorSuite suite, MonitorCaseRunner runner) {
        try {
            suite.addCase(runner.run());
        } catch (Exception e) {
            createCaseWithErrorFromExceptionAndAdd(suite, e);
        }
    }

    private void createCaseWithErrorFromExceptionAndAdd(MonitorSuite suite, Throwable e) {
        MonitorCase monitorCase = new MonitorCase();
        monitorCase.name = e.getClass().getName();
        MonitorStacktrace error = new MonitorStacktrace();
        error.message = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        monitorCase.error = error;
        suite.addCase(monitorCase);
    }
}