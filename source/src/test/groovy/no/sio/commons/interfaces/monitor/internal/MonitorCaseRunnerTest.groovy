package no.sio.commons.interfaces.monitor.internal;


import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase
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
    final String FORVENTET_EXCEPTION_MESSAGE = "Exception laget av mockito";
    @Mock
    MonitorCaseRunner runner;

    @Test
    public void kjor_skal_catche_alle_exceptions() {
        when(runner.kjor()).thenCallRealMethod();
        when(runner.kjorInternally()).thenThrow(new RuntimeException(FORVENTET_EXCEPTION_MESSAGE));

        MonitorCase monitorCase = runner.kjor();
        assertThat(monitorCase, notNullValue());
        assertThat(monitorCase.error, notNullValue());
        assertThat(monitorCase.error.message, equalTo(FORVENTET_EXCEPTION_MESSAGE));
        assertThat(monitorCase.error.stacktrace, containsString(getClass().name));
    }
}