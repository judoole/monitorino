package com.github.judoole.healthcheck.cases;

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.dto.HealthcheckFailureCase;

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
