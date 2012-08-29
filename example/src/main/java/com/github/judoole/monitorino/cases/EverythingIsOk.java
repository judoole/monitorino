package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.HealthcheckCaseRunner;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;

public class EverythingIsOk extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "TestCase thats goes OK.";
    }

    @Override
    protected MonitorinoFailureCase assertNoFailure() {
        return null;
    }
}
