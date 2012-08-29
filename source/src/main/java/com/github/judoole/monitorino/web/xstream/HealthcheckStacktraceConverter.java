package com.github.judoole.monitorino.web.xstream;

import com.github.judoole.monitorino.internal.dto.HealthcheckStacktrace;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class HealthcheckStacktraceConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(HealthcheckStacktrace.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        HealthcheckStacktrace healthcheckStacktrace = (HealthcheckStacktrace) value;
        if (healthcheckStacktrace.message != null) writer.addAttribute("message", healthcheckStacktrace.message);
        if (healthcheckStacktrace.stacktrace != null) writer.setValue(healthcheckStacktrace.stacktrace);
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        //Screw you. We don't need to unmarshall
        return null;
    }

}