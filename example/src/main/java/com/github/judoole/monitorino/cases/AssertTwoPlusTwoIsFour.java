package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;

public class AssertTwoPlusTwoIsFour extends MonitorinoRunner {
    @Override
    protected String getName() {
        return "Case for two plus two";
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
