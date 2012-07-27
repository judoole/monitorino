package no.sio.commons.interfaces.monitor.web

import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnit44Runner
import static org.hamcrest.core.IsNull.notNullValue
import static org.hamcrest.core.StringContains.containsString
import static org.junit.Assert.assertThat
import static org.mockito.Mockito.when

@RunWith(MockitoJUnit44Runner.class)
public class MonitorControllerHtmlViewTest {
    @Mock
    MonitorController controller;
    MonitorSuite suite;
    String prosessResultat;

    @Test
    public void skal_kunne_lage_en_html_side_med_alle_monitorresultat() {
        gitt_at_jeg_har_en_suite()
        naar_jeg_prosesserer_suiten_for_html_resultat()
        saa_skal_ikke_view_returnere_en_tom_body();
        saa_skal_resultatet_inneholde_suite_navnet();
        saa_skal_resultatet_inneholde_riktig_case();
        saa_skal_resultatet_inneholde_en_feil_case();
    }

    private void gitt_at_jeg_har_en_suite() {
        suite = new MonitorSuite();
        suite.name = "What a suite name";
        suite.leggTilCase(new MonitorCase(name: "Just another bad case of leprecauns", error: new MonitorStacktrace()));
        when(controller.kjorSuite()).thenReturn(suite);
        when(controller.htmlRapport()).thenCallRealMethod();
    }

    private void naar_jeg_prosesserer_suiten_for_html_resultat() {
        this.prosessResultat = controller.htmlRapport();
    }

    private def saa_skal_ikke_view_returnere_en_tom_body() {
        assertThat(prosessResultat, notNullValue())
    }

    private void saa_skal_resultatet_inneholde_suite_navnet() {
        assertThat(prosessResultat, containsString(suite.name));
    }

    private void saa_skal_resultatet_inneholde_riktig_case() {
        assertThat(prosessResultat, containsString(suite.monitorCases[0].name));
    }

    private void saa_skal_resultatet_inneholde_en_feil_case() {
        assertThat(prosessResultat, containsString(".error"));
    }
}