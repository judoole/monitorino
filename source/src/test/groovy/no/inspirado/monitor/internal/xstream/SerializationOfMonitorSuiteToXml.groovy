package no.inspirado.monitor.internal.xstream

import com.thoughtworks.xstream.XStream
import no.inspirado.monitor.internal.dto.MonitorCase
import no.inspirado.monitor.internal.dto.MonitorStacktrace
import no.inspirado.monitor.internal.dto.MonitorSuite

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
                        new MonitorCase(name: "case 2", error: new MonitorStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")),
                        new MonitorCase(name: "case 3. The good case")]
        );
        System.out.println(stream.toXML(msg));
    }
}