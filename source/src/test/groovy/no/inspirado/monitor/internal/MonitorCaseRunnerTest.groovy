package no.inspirado.monitor.internal;


import no.inspirado.monitor.facade.dto.MonitorCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnit44Runner
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.core.IsNull.notNullValue
import static org.junit.Assert.assertThat
import static org.junit.internal.matchers.StringContains.containsString
import static org.mockito.Mockito.when

@RunWith(MockitoJUnit44Runner.class)
public class MonitorCaseRunnerTest {
    final String EXPECTED_EXCEPTION_MESSAGE = "Exception made by mockito";
    @Mock
    MonitorCaseRunner runner;

    @Test
    public void run_should_catch_all_exceptions() {
        when(runner.run()).thenCallRealMethod();
        when(runner.runInternally()).thenThrow(new RuntimeException(EXPECTED_EXCEPTION_MESSAGE));

        MonitorCase monitorCase = runner.run();
        assertThat(monitorCase, notNullValue());
        assertThat(monitorCase.error, notNullValue());
        assertThat(monitorCase.error.message, equalTo(EXPECTED_EXCEPTION_MESSAGE));
        assertThat(monitorCase.error.stacktrace, containsString(getClass().name));
    }
}