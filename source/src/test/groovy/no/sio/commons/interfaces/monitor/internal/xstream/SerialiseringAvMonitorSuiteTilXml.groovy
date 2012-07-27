package no.sio.commons.interfaces.monitor.internal.xstream

import com.thoughtworks.xstream.XStream
import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite

class SerialiseringAvMonitorSuiteTilXml {
    public static void main(String... args) {
        XStream stream = new XStream();
        stream.autodetectAnnotations(true);
        MonitorSuite msg = new MonitorSuite(
                navn: "Min monitorsuite",
                monitorCases: [new MonitorCase(navn: "case1", feil: new MonitorStacktrace(beskjed: "Ting gikk langt fra bra")),
                        new MonitorCase(navn: "case 2", error: new MonitorStacktrace(beskjed: "Ting gikk ikke bra nå heller", stacktrace: "Bla, bla, BLAM!")),
                        new MonitorCase(navn: "case 3. The good case")]
        );
        System.out.println(stream.toXML(msg));
    }
}