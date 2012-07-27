package no.sio.commons.interfaces.monitor.internal.xstream

import com.thoughtworks.xstream.XStream
import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase
import no.sio.commons.interfaces.monitor.facade.dto.MonitorStacktrace
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite

/*
Helper class for testing xml output.
 */
class SerializationOfMonitorSuiteToXml {
    public static void main(String... args) {
        XStream stream = new XStream();
        stream.autodetectAnnotations(true);
        MonitorSuite msg = new MonitorSuite(
                name: "My monitorsuite",
                monitorCases: [new MonitorCase(name: "case1", failure: new MonitorStacktrace(message: "Things went far from good")),
                        new MonitorCase(name: "case 2", error: new MonitorStacktrace(message: "Things didn't go so well no either", stacktrace: "Bla, bla, BLAM!")),
                        new MonitorCase(name: "case 3. The good case")]
        );
        System.out.println(stream.toXML(msg));
    }
}