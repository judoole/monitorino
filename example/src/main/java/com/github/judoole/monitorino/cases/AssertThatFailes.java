package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;

public class AssertThatFailes extends MonitorinoRunner {
    @Override
    protected String getName() {
        return "Case that failes due to bad arithmetic";
    }

    @Override
    protected MonitorinoFailureCase assertNoFailure() {
        if (5 + 5 == 4) {
            return null;
        } else {
            return new MonitorinoFailureCase("Expected 5 + 5 to be equal to 4.");
        }

    }
}
