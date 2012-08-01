package com.github.judoole.healthcheck.cases;

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.dto.HealthcheckFailureCase;

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
