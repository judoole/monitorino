package no.sio.commons.interfaces.monitor.web

import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite
import no.sio.commons.interfaces.monitor.internal.MonitorCaseRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnit44Runner
import static org.hamcrest.Matchers.*
import static org.hamcrest.core.IsNot.not
import static org.junit.Assert.assertThat
import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnit44Runner.class)
class MonitorControllerTest {
    private static final String FORVENTET_EXCEPTION_MESSAGE = "Exception som mockito laget for test"
    @Mock
    MonitorCaseRunner monitorCaseRunner;
    MonitorController controller;
    MonitorSuite suite;

    @Test
    void skal_kunne_lage_en_monitor_suite() {
        gitt_at_kontroller_er_laget();
        gitt_at_runner_returnerer_suksessfullt_case();
        naar_jeg_kjorer_controlleren();
        saa_skal_jeg_faa_tilbake_en_suite();
        saa_skal_suiten_ha_navn();
        saa_skal_suiten_ha_riktig_antall_tester();
        saa_skal_suiten_ha_tatt_kjoretiden();
    }

    @Test
    void skal_ikke_krasje_selv_om_runner_kaster_exception() {
        gitt_at_kontroller_er_laget();
        gitt_at_runner_kaster_en_exception();
        naar_jeg_kjorer_controlleren();
        saa_skal_jeg_faa_tilbake_en_suite();
        saa_skal_suiten_ha_errors();
        saa_skal_stacktrace_ha_blitt_lagret_paa_case();
    }

    @Test
    void skal_telle_opp_caser_som_feiler() {
        gitt_at_kontroller_er_laget();
        gitt_at_runner_returnerer_case_som_har_feilet();
        naar_jeg_kjorer_controlleren();
        saa_skal_jeg_faa_tilbake_en_suite();
        saa_skal_suiten_ha_feil();
    }

    private void gitt_at_kontroller_er_laget() {
        controller = new MonitorController([monitorCaseRunner]);
    }

    private void gitt_at_runner_returnerer_suksessfullt_case() {
        given(monitorCaseRunner.kjor()).willReturn(new MonitorCase())
    }

    private void gitt_at_runner_kaster_en_exception() {
        given(monitorCaseRunner.kjor()).willThrow(new RuntimeException(FORVENTET_EXCEPTION_MESSAGE))
    }

    private void gitt_at_runner_returnerer_case_som_har_feilet() {
        MonitorCase feiletMonitorCase = new MonitorCase();
        feiletMonitorCase.failure = new MonitorStacktrace();
        given(monitorCaseRunner.kjor()).willReturn(feiletMonitorCase);
    }

    private void naar_jeg_kjorer_controlleren() {
        suite = controller.kjorSuite()
    }

    private def saa_skal_jeg_faa_tilbake_en_suite() {
        assertThat(suite, notNullValue())
    }

    private void saa_skal_suiten_ha_navn() {
        assertThat(suite.name, notNullValue());
    }
    private void saa_skal_suiten_ha_riktig_antall_tester() {
        assertThat(suite.numberOfTests, equalTo(1));
    }

    private void saa_skal_suiten_ha_tatt_kjoretiden() {
        assertThat(suite.timeInSeconds, notNullValue());
    }

    private void saa_skal_suiten_ha_errors() {
        assertThat(suite.numberOfErrors, equalTo(1));
        assertThat(suite.numberOfFailures, equalTo(0));
    }

    private void saa_skal_suiten_ha_feil() {
        assertThat(suite.numberOfErrors, equalTo(0));
        assertThat(suite.numberOfFailures, equalTo(1));
    }

    private void saa_skal_stacktrace_ha_blitt_lagret_paa_case() {
        Collection<MonitorCase> cases = suite.monitorCases;
        assertThat(cases, notNullValue());
        assertThat(cases, not(empty()));
        MonitorStacktrace error = cases.iterator().next().error
        assertThat(error, notNullValue());
        assertThat(error.message, equalTo(FORVENTET_EXCEPTION_MESSAGE));
        assertThat(error.stacktrace, containsString(getClass().name));
    }
}