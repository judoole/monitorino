package no.inspirado.healthcheck.cases;

import no.inspirado.healthcheck.internal.HealthcheckCaseRunner;
import no.inspirado.healthcheck.internal.dto.HealthcheckFailureCase;

public class HealthcheckThatFailes extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "HealthcheckCase that fails";
    }

    @Override
    protected HealthcheckFailureCase assertNoFailure() {
        return new HealthcheckFailureCase("Failed because I told i to");
    }
}
