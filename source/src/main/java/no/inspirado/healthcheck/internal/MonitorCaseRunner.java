package no.inspirado.healthcheck.internal;

import no.inspirado.healthcheck.internal.dto.MonitorCase;
import no.inspirado.healthcheck.internal.dto.MonitorFailureCase;
import no.inspirado.healthcheck.internal.dto.MonitorStacktrace;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class MonitorCaseRunner {
    public MonitorCase run() {
        StopWatch stopWatch = new StopWatch().start();
        MonitorCase monitorCase = new MonitorCase();
        monitorCase.name = getName();
        try {
            MonitorFailureCase failure = assertNoFailure();
            if(failure != null){
                monitorCase.failure = new MonitorStacktrace();
                monitorCase.failure.message = failure.reason;
            }
        } catch (Exception e) {
            MonitorStacktrace error = new MonitorStacktrace();
            error.message = e.getMessage();
            error.stacktrace = ExceptionUtils.getStackTrace(e);
            monitorCase.error = error;
        }
        monitorCase.time = stopWatch.stop().timeInSeconds().toString();
        return monitorCase;
    }

    protected abstract String getName();

    protected abstract MonitorFailureCase assertNoFailure();
}