package com.github.judoole.monitorino.web.xstream;

import java.io.Writer;

public class JsonWriter extends com.thoughtworks.xstream.io.json.JsonWriter {
    public JsonWriter(Writer writer, int mode) {
        super(writer, mode);
    }

    public JsonWriter(Writer writer, int mode, Format format) {
        super(writer, mode, format);
    }

    public void startObject() {
        super.startObject();
    }

    public void addLabel(String label) {
        super.addLabel(label);
    }

    public void addValue(String value, Type type) {
        super.addValue(value, Type.STRING);
    }

    public void endObject() {
        super.endObject();
    }

    public void writeSeparator() {
        super.writer.write(",");
    }
}
