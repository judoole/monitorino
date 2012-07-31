package no.inspirado.healthcheck.cases;

import no.inspirado.healthcheck.internal.MonitorCaseRunner;
import no.inspirado.healthcheck.internal.dto.MonitorFailureCase;

public class MonitorCaseThatThrowsRuntimeException extends MonitorCaseRunner{
    @Override
    protected String getName() {
        return "MonitorCase that throws RuntimeException";
    }

    @Override
    protected MonitorFailureCase assertNoFailure() {
        throw new RuntimeException("I deliberately threw this exception");
    }
}
