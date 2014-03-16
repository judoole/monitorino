package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.dto.Case;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;

import java.util.*;

import static com.github.judoole.monitorino.web.util.MonitorinoTemplater.replaceTokens;
import static com.github.judoole.monitorino.web.util.MonitorinoTemplater.template;

/**
 * Okey. So maybe I hate template frameworks also now? Nike. Just Do It. Yourself.
 */
public class HtmlView {
    private static final String TEMPLATE_FOR_HEALTHCHECK_CASER = "<case id=\"case[id]\" class=\"[isSuccess]\"><name onclick=\"showHideCaseInfo('case[id]')\">[name]</name><info>[info]</info></case>\n";
    private static final String TEMPLATE_FOR_HEALTHCHECK_PROPERTY = "<property><name>[name]</name><value>[value]</value></property>\n";

    public String process(MonitorinoSuite suite) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", suite.name);
        map.put("cases", createHtmlForCases(suite.testCases));
        map.put("properties", createHtmlForProperties(suite.healthcheckProperties));

        return replaceTokens(template("/com/github/judoole/healthcheckViewTemplate.html"), map);
    }

    private String createHtmlForCases(Collection<Case> cases) {
        if(cases == null) return "";

        StringBuilder html = new StringBuilder();
        int casenumber=0;
        for (Case testCase : cases) {
            Map<String, Object> replaceMap = new HashMap<String, Object>();
            replaceMap.put("name", (Object) testCase.name);
            replaceMap.put("id", ++casenumber);
            if (testCase.hasError()) {
                replaceMap.put("isSuccess", "error hide");
                replaceMap.put("info", testCase.error.stacktrace);
            } else if (testCase.hasFailure()) {
                replaceMap.put("isSuccess", "failure hide");
                replaceMap.put("info", testCase.failure.message);
            } else {
                replaceMap.put("isSuccess", "success");
                replaceMap.put("info", "");
            }
            String aNewRow = replaceTokens(TEMPLATE_FOR_HEALTHCHECK_CASER, replaceMap);
            html.append(aNewRow);
        }

        return html.toString();
    }

    private String createHtmlForProperties(Properties properties) {
        if (properties == null) return "";

        StringBuilder html = new StringBuilder();
        Enumeration<?> keys = properties.propertyNames();
        while (keys.hasMoreElements()) {
            Map<String, Object> replaceMap = new HashMap<String, Object>();
            Object name = keys.nextElement();
            replaceMap.put("name", name);
            replaceMap.put("value", properties.getProperty((String) name));
            String aNewRow = replaceTokens(TEMPLATE_FOR_HEALTHCHECK_PROPERTY, replaceMap);
            html.append(aNewRow);
        }

        return html.toString();
    }

}