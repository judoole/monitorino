package no.inspirado.healthcheck.internal;


import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnit44Runner
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.core.IsNull.notNullValue
import static org.junit.Assert.assertThat
import static org.junit.internal.matchers.StringContains.containsString
import static org.mockito.Mockito.when

import static org.hamcrest.core.IsNull.nullValue
import no.inspirado.healthcheck.internal.dto.HealthcheckCase
import no.inspirado.healthcheck.internal.dto.HealthcheckFailureCase

@RunWith(MockitoJUnit44Runner.class)
public class HealthcheckCaseRunnerTest {
    final String EXPECTED_EXCEPTION_MESSAGE = "Exception made by mockito";
    @Mock
    HealthcheckCaseRunner runner;

    @Test
    public void run_should_catch_all_exceptions() {
        when(runner.run()).thenCallRealMethod();
        when(runner.assertNoFailure()).thenThrow(new RuntimeException(EXPECTED_EXCEPTION_MESSAGE));

        HealthcheckCase monitorCase = runner.run();
        assertThat(monitorCase, notNullValue());
        assertThat(monitorCase.error, notNullValue());
        assertThat(monitorCase.error.message, equalTo(EXPECTED_EXCEPTION_MESSAGE));
        assertThat(monitorCase.error.stacktrace, containsString(getClass().name));
    }

    @Test
    public void run_should_create_case_with_failure() {
        when(runner.run()).thenCallRealMethod();
        when(runner.assertNoFailure()).thenReturn(new HealthcheckFailureCase("Testcase"));

        HealthcheckCase monitorCase = runner.run();
        assertThat(monitorCase, notNullValue());
        assertThat(monitorCase.error, nullValue());
        assertThat(monitorCase.failure.message, equalTo("Testcase"));
    }

    @Test
    public void run_should_create_case_with_success() {
        when(runner.run()).thenCallRealMethod();
        when(runner.assertNoFailure()).thenReturn(null);

        HealthcheckCase monitorCase = runner.run();
        assertThat(monitorCase, notNullValue());
        assertThat(monitorCase.error, nullValue());
        assertThat(monitorCase.failure, nullValue());
    }
}