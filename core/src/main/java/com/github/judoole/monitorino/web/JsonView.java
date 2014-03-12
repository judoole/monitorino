package com.github.judoole.monitorino.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;

import java.io.IOException;

public class JsonView {

    ObjectMapper objectMapper = new ObjectMapper();

    public String process(MonitorinoSuite suite) {
        try {
            return process(suite, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    String process(MonitorinoSuite suite, boolean prettyPrint) throws IOException {
        if (!prettyPrint) {
            return objectMapper.writer().writeValueAsString(suite);
        } else {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(suite);
        }
    }
}
