package no.inspirado.monitor.internal.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import no.inspirado.monitor.internal.dto.MonitorStacktrace;

public class MonitorStacktraceConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(MonitorStacktrace.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        MonitorStacktrace monitorStacktrace = (MonitorStacktrace) value;
        if (monitorStacktrace.message != null) writer.addAttribute("message", monitorStacktrace.message);
        if (monitorStacktrace.stacktrace != null) writer.setValue(monitorStacktrace.stacktrace);
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        //Screw you. Vi trenger ikke unmarshalle.
        return null;
    }

}