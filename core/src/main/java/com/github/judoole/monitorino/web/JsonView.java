package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;
import com.github.judoole.monitorino.web.xstream.PropertiesToJsonConverter;
import com.github.judoole.monitorino.web.xstream.JsonWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import java.io.IOException;
import java.io.Writer;

public class JsonView {

    public String process(MonitorinoSuite suite) {
        try {
            return process(suite, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    String process(MonitorinoSuite suite, final boolean prettyPrint) throws IOException {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                if (!prettyPrint) {
                    return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE,
                            new JsonWriter.Format(new char[]{}, new char[]{}, JsonWriter.Format.SPACE_AFTER_LABEL));
                } else {
                    return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
                }
            }
        });
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.registerConverter(new PropertiesToJsonConverter());

        return xstream.toXML(suite);
    }
}
