package no.sio.commons.interfaces.monitor.internal;

import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Collection;

public class MonitorSuiteAssembler {
    private final Collection<MonitorCaseRunner> monitorCaseRunners;
    private final String suiteName;

    public MonitorSuiteAssembler(String monitorName, Collection<MonitorCaseRunner> monitorCaseRunners) {
        this.monitorCaseRunners = monitorCaseRunners;
        this.suiteName = monitorName;
    }

    public MonitorSuite run() {
        MonitorSuite suite = new MonitorSuite(suiteName);
        StopWatch stopWatch = new StopWatch().start();
        for (MonitorCaseRunner runner : monitorCaseRunners) {
            runCase(suite, runner);
        }
        suite.timeInSeconds = stopWatch.stop().timeInSeconds().toString();
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