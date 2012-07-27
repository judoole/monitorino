package no.sio.commons.interfaces.monitor.internal;

import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class MonitorCaseRunner {
    public MonitorCase kjor() {
        StopWatch stopWatch = new StopWatch().start();
        MonitorCase monitorCase;
        try {
            monitorCase = kjorInternally();
        } catch (Exception e) {
            monitorCase = lagCaseMedErrorAvException(e);
        }
        monitorCase.tidISekunder = stopWatch.stop().timeInSeconds().toString();
        return monitorCase;
    }

    protected abstract MonitorCase kjorInternally();

    private MonitorCase lagCaseMedErrorAvException(Throwable e) {
        MonitorCase monitorCase = new MonitorCase();
        monitorCase.navn = e.getClass().getName();
        MonitorStacktrace error = new MonitorStacktrace();
        error.beskjed = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        monitorCase.error = error;

        return monitorCase;
    }
}