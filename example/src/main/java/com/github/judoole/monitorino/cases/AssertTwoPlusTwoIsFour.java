package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.HealthcheckCaseRunner;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;

public class AssertTwoPlusTwoIsFour extends HealthcheckCaseRunner {
    @Override
    protected String getName() {
        return "TestCase for two plus two";
    }

    @Override
    protected MonitorinoFailureCase assertNoFailure() {
        if (2 + 2 == 4) {
            return null;
        } else {
            return new MonitorinoFailureCase("Two plus two is quite not four.");
        }

    }
}
