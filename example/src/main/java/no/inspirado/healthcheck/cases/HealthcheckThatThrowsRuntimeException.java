package no.inspirado.healthcheck.cases;

import no.inspirado.healthcheck.internal.HealthcheckCaseRunner;
import no.inspirado.healthcheck.internal.dto.HealthcheckFailureCase;

public class HealthcheckThatThrowsRuntimeException extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "HealthcheckCase that throws RuntimeException";
    }

    @Override
    protected HealthcheckFailureCase assertNoFailure() {
        throw new RuntimeException("I deliberately threw this exception");
    }
}
