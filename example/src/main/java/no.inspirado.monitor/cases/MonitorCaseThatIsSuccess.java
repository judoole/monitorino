package no.inspirado.monitor.cases;

import no.inspirado.monitor.internal.MonitorCaseRunner;
import no.inspirado.monitor.internal.dto.MonitorFailureCase;

public class MonitorCaseThatIsSuccess extends MonitorCaseRunner{
    @Override
    protected String getName() {
        return "MonitorCase thats goes OK.";
    }

    @Override
    protected MonitorFailureCase assertNoFailure() {
        return null;
    }
}
