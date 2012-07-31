package no.inspirado.healthcheck.internal;

import no.inspirado.healthcheck.internal.dto.HealthcheckCase;
import no.inspirado.healthcheck.internal.dto.HealthcheckFailureCase;
import no.inspirado.healthcheck.internal.dto.HealthcheckStacktrace;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class HealthcheckCaseRunner {
    public HealthcheckCase run() {
        StopWatch stopWatch = new StopWatch().start();
        HealthcheckCase healthcheckCase = new HealthcheckCase();
        healthcheckCase.name = getName();
        try {
            HealthcheckFailureCase failure = assertNoFailure();
            if(failure != null){
                healthcheckCase.failure = new HealthcheckStacktrace();
                healthcheckCase.failure.message = failure.reason;
            }
        } catch (Exception e) {
            HealthcheckStacktrace error = new HealthcheckStacktrace();
            error.message = e.getMessage();
            error.stacktrace = ExceptionUtils.getStackTrace(e);
            healthcheckCase.error = error;
        }
        healthcheckCase.time = stopWatch.stop().timeInSeconds().toString();
        return healthcheckCase;
    }

    protected abstract String getName();

    protected abstract HealthcheckFailureCase assertNoFailure();
}