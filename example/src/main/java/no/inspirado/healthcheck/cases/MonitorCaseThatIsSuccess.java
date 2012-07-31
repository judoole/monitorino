package no.inspirado.healthcheck.cases;

import no.inspirado.healthcheck.internal.MonitorCaseRunner;
import no.inspirado.healthcheck.internal.dto.MonitorFailureCase;

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
