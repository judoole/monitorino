package no.inspirado.healthcheck.cases;

import no.inspirado.healthcheck.internal.HealthcheckCaseRunner;
import no.inspirado.healthcheck.internal.dto.HealthcheckFailureCase;

public class HealthcheckThatIsSuccess extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "HealthcheckCase thats goes OK.";
    }

    @Override
    protected HealthcheckFailureCase assertNoFailure() {
        return null;
    }
}
