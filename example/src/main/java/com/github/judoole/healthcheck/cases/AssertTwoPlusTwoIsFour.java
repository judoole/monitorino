package com.github.judoole.healthcheck.cases;

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.dto.HealthcheckFailureCase;

public class AssertTwoPlusTwoIsFour extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "HealthcheckCase for two plus two";
    }

    @Override
    protected HealthcheckFailureCase assertNoFailure() {
        if (2 + 2 == 4) {
            return null;
        } else {
            return new HealthcheckFailureCase("Two plus two is quite not four.");
        }

    }
}
