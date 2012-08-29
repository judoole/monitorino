package com.github.judoole.monitorino.internal;


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

import com.github.judoole.monitorino.internal.dto.MonitorinoFailureCase
import com.github.judoole.monitorino.internal.dto.Case

@RunWith(MockitoJUnit44Runner.class)
public class MonitorinoRunnerTest {
    final String EXPECTED_EXCEPTION_MESSAGE = "Exception made by mockito";
    @Mock
    MonitorinoRunner runner;

    @Test
    public void run_should_catch_all_exceptions() {
        when(runner.run()).thenCallRealMethod();
        when(runner.assertNoFailure()).thenThrow(new RuntimeException(EXPECTED_EXCEPTION_MESSAGE));

        Case monitorinoCase = runner.run();
        assertThat(monitorinoCase, notNullValue());
        assertThat(monitorinoCase.error, notNullValue());
        assertThat(monitorinoCase.error.message, equalTo(EXPECTED_EXCEPTION_MESSAGE));
        assertThat(monitorinoCase.error.stacktrace, containsString(getClass().name));
    }

    @Test
    public void run_should_create_case_with_failure() {
        when(runner.run()).thenCallRealMethod();
        when(runner.assertNoFailure()).thenReturn(new MonitorinoFailureCase("Testcase"));

        Case monitorinoCase = runner.run();
        assertThat(monitorinoCase, notNullValue());
        assertThat(monitorinoCase.error, nullValue());
        assertThat(monitorinoCase.failure.message, equalTo("Testcase"));
    }

    @Test
    public void run_should_create_case_with_success() {
        when(runner.run()).thenCallRealMethod();
        when(runner.assertNoFailure()).thenReturn(null);

        Case healthcheckCase = runner.run();
        assertThat(healthcheckCase, notNullValue());
        assertThat(healthcheckCase.error, nullValue());
        assertThat(healthcheckCase.failure, nullValue());
    }

    @Test
    public void run_should_be_able_to_have_empty_cases_and_empty_properties() {
        when(runner.run()).thenCallRealMethod();

        Case healthcheckCase = runner.run();
        assertThat(healthcheckCase, notNullValue());
        assertThat(healthcheckCase.error, nullValue());
        assertThat(healthcheckCase.failure, nullValue());
    }
}