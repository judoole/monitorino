package com.github.judoole.monitorino.web.xstream;

import com.github.judoole.monitorino.internal.dto.Stacktrace;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class StacktraceConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return clazz.equals(Stacktrace.class);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Stacktrace stacktrace = (Stacktrace) value;
        if (stacktrace.message != null) writer.addAttribute("message", stacktrace.message);
        if (stacktrace.stacktrace != null) writer.setValue(stacktrace.stacktrace);
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        //Screw you. We don't need to unmarshall
        return null;
    }

}