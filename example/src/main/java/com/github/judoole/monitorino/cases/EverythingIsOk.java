package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.HealthcheckCaseRunner;
import com.github.judoole.monitorino.internal.dto.HealthcheckFailureCase;

public class EverythingIsOk extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "HealthcheckCase thats goes OK.";
    }

    @Override
    protected HealthcheckFailureCase assertNoFailure() {
        return null;
    }
}
