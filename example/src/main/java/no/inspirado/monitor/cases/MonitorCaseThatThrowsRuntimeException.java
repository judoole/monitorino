package no.inspirado.monitor.cases;

import no.inspirado.monitor.internal.MonitorCaseRunner;
import no.inspirado.monitor.internal.dto.MonitorFailureCase;

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
