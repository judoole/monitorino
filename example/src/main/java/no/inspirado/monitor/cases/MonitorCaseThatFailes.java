package no.inspirado.monitor.cases;

import no.inspirado.monitor.internal.MonitorCaseRunner;
import no.inspirado.monitor.internal.dto.MonitorFailureCase;

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
