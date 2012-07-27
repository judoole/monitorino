package no.sio.commons.interfaces.monitor.internal;

import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Collection;

public class MonitorSuiteAssembler {
    private final Collection<MonitorCaseRunner> monitorCaseRunners;
    private final String suiteNavn;

    public MonitorSuiteAssembler(String monitorNavn, Collection<MonitorCaseRunner> monitorCaseRunners) {
        this.monitorCaseRunners = monitorCaseRunners;
        this.suiteNavn = monitorNavn;
    }

    public MonitorSuite kjor() {
        MonitorSuite suite = new MonitorSuite(suiteNavn);
        StopWatch stopWatch = new StopWatch().start();
        for (MonitorCaseRunner runner : monitorCaseRunners) {
            kjorCase(suite, runner);
        }
        suite.tidISekunder = stopWatch.stop().timeInSeconds().toString();
        return suite;
    }

    private void kjorCase(MonitorSuite suite, MonitorCaseRunner runner) {
        try {
            suite.leggTilCase(runner.kjor());
        } catch (Exception e) {
            lagCaseMedErrorAvExceptionOgLeggTil(suite, e);
        }
    }

    private void lagCaseMedErrorAvExceptionOgLeggTil(MonitorSuite suite, Throwable e) {
        MonitorCase monitorCase = new MonitorCase();
        monitorCase.navn = e.getClass().getName();
        MonitorStacktrace error = new MonitorStacktrace();
        error.beskjed = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        monitorCase.error = error;
        suite.leggTilCase(monitorCase);
    }
}