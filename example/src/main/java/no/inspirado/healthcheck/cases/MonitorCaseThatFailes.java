package no.inspirado.healthcheck.cases;

import no.inspirado.healthcheck.internal.MonitorCaseRunner;
import no.inspirado.healthcheck.internal.dto.MonitorFailureCase;

public class MonitorCaseThatFailes extends MonitorCaseRunner{
    @Override
    protected String getName() {
        return "MonitorCase that fails";
    }

    @Override
    protected MonitorFailureCase assertNoFailure() {
        return new MonitorFailureCase("Failed because I told i to");
    }
}
