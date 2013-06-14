package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;

public class AssertThatThrowsRuntimeException extends MonitorinoRunner {
    @Override
    protected String getName() {
        return "Case that throws RuntimeException";
    }

    @Override
    protected MonitorinoFailureCase assertNoFailure() {
        throw new RuntimeException("Deliberately threw this exception");

    }
}
