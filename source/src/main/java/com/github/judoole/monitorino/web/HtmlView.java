package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.dto.HealthcheckCase;
import com.github.judoole.monitorino.internal.dto.HealthcheckSuite;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Okey. So maybe I hate template frameworks also now? Nike. Just Do It. Yourself.
 */
public class HtmlView {
    private static final String TEMPLATE_FOR_HEALTHCHECK_CASER = "<tr><td>[name]</td><td class=\"[isSuccess]\"/></tr>\n";
    private static final String TEMPLATE_FOR_HEALTHCHECK_PROPERTY = "<tr><td><b>[name]:</b></td><td>[value]</td></tr>\n";

    public String process(HealthcheckSuite suite) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", suite.name);
        map.put("cases", createHtmlForCases(suite.healthcheckCases));
        map.put("properties", createHtmlForProperties(suite.healthcheckProperties));

        return replaceTokens(template(), map);
    }

    private String template() {
        try {
            return IOUtils.toString(getClass().getResourceAsStream("/com/github/judoole/healthcheckViewTemplate.html"), "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createHtmlForCases(Collection<HealthcheckCase> cases) {
        if(cases == null) return "";

        StringBuilder html = new StringBuilder();
        for (HealthcheckCase healthcheckCase : cases) {
            Map<String, Object> replaceMap = new HashMap<String, Object>();
            replaceMap.put("name", (Object) healthcheckCase.name);
            if (healthcheckCase.hasError()) {
                replaceMap.put("isSuccess", "error");
            } else if (healthcheckCase.hasFailure()) {
                replaceMap.put("isSuccess", "warning");
            } else {
                replaceMap.put("isSuccess", "success");
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

    private String replaceTokens(String text, Map<String, ?> replacements) {
        Pattern pattern = Pattern.compile("\\[(.+?)\\]");
        Matcher matcher = pattern.matcher(text);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            Object replacement = replacements.get(matcher.group(1));
            if (replacement != null) {
                matcher.appendReplacement(buffer, "");
                buffer.append(replacement.toString());
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}