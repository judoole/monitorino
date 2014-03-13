package com.github.judoole.monitorino.web.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.AbstractJsonWriter;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesToJsonConverter implements Converter {

    @Override
    public boolean canConvert(Class clazz) {
        return Properties.class.equals(clazz);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        final Properties properties = (Properties) source;
        JsonWriter jsonWriter = (JsonWriter) writer.underlyingWriter();
        jsonWriter.startObject();
        for (Iterator iterator = properties.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            jsonWriter.addLabel(entry.getKey().toString());
            jsonWriter.addValue(entry.getValue().toString(), AbstractJsonWriter.Type.STRING);
            if (iterator.hasNext()) {
                jsonWriter.writeSeparator();
            }
        }
        jsonWriter.endObject();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }
}
