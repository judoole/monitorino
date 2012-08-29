package com.github.judoole.monitorino.internal;

import com.github.judoole.monitorino.internal.dto.Stacktrace;
import com.github.judoole.monitorino.internal.dto.TestCase;
import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class HealthcheckCaseRunner {
    public TestCase run() {
        StopWatch stopWatch = new StopWatch().start();
        TestCase testCase = new TestCase();
        testCase.name = getName();
        try {
            MonitorinoFailureCase failure = assertNoFailure();
            if(failure != null){
                testCase.failure = new Stacktrace();
                testCase.failure.message = failure.reason;
            }
        } catch (Exception e) {
            Stacktrace error = new Stacktrace();
            error.message = e.getMessage();
            error.stacktrace = ExceptionUtils.getStackTrace(e);
            testCase.error = error;
        }
        testCase.time = stopWatch.stop().timeInSeconds().toString();
        return testCase;
    }

    protected abstract String getName();

    protected abstract MonitorinoFailureCase assertNoFailure();
}