package no.inspirado.monitor.internal;

import no.inspirado.monitor.internal.dto.MonitorCase;
import no.inspirado.monitor.internal.dto.MonitorStacktrace;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class MonitorCaseRunner {
    public MonitorCase run() {
        StopWatch stopWatch = new StopWatch().start();
        MonitorCase monitorCase;
        try {
            monitorCase = runInternally();
        } catch (Exception e) {
            monitorCase = createCaseWithErrorFromException(e);
        }
        monitorCase.time = stopWatch.stop().timeInSeconds().toString();
        return monitorCase;
    }

    protected abstract MonitorCase runInternally();

    private MonitorCase createCaseWithErrorFromException(Throwable e) {
        MonitorCase monitorCase = new MonitorCase();
        monitorCase.name = e.getClass().getName();
        MonitorStacktrace error = new MonitorStacktrace();
        error.message = e.getMessage();
        error.stacktrace = ExceptionUtils.getStackTrace(e);
        monitorCase.error = error;

        return monitorCase;
    }
}