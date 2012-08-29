package com.github.judoole.monitorino.cases;

import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;

public class EverythingIsOk extends MonitorinoRunner {
    @Override
    protected String getName() {
        return "Case thats goes OK.";
    }

    @Override
    protected MonitorinoFailureCase assertNoFailure() {
        return null;
    }
}
