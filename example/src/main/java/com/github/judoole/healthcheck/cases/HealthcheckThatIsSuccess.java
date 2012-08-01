package com.github.judoole.healthcheck.cases;

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.dto.HealthcheckFailureCase;

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
