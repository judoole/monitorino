package no.inspirado.monitor.web;

import no.inspirado.monitor.internal.dto.MonitorCase;
import no.inspirado.monitor.internal.dto.MonitorProperty;
import no.inspirado.monitor.internal.dto.MonitorSuite;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Okey. So maybe I hate template frameworks also now? Nike. Just Do It. Yourself.
 */
public class MonitorHtmlView {
    private static final String TEMPLATE_FOR_MONITOR_CASER = "<tr><td>[name]</td><td class=\"[isSuccess]\"/></tr>\n";
    private static final String TEMPLATE_FOR_MONITOR_PROPERTY = "<tr><td><b>[name]:</b></td><td>[value]</td></tr>\n";

    public String process(MonitorSuite suite) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", suite.name);
        map.put("cases", createHtmlForCases(suite.monitorCases));
        map.put("properties", createHtmlForProperties(suite.monitorProperties));

        return replaceTokens(template(), map);
    }

    private String template() {
        try {
            return IOUtils.toString(getClass().getResourceAsStream("/no/inspirado/monitor/web/monitorViewTemplate.html"), "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createHtmlForCases(Collection<MonitorCase> monitorCases) {
        StringBuilder html = new StringBuilder();
        for (MonitorCase monitorCase : monitorCases) {
            Map<String, Object> replaceMap = new HashMap<String, Object>();
            replaceMap.put("name", (Object) monitorCase.name);
            if (monitorCase.hasError()) {
                replaceMap.put("isSuccess", "error");
            } else if(monitorCase.hasFailure()){
                replaceMap.put("isSuccess", "warning");
            }
            else {
                replaceMap.put("isSuccess", "success");
            }
            String aNewRow = replaceTokens(TEMPLATE_FOR_MONITOR_CASER, replaceMap);
            html.append(aNewRow);
        }

        return html.toString();
    }

    private String createHtmlForProperties(Collection<MonitorProperty> monitorCases) {
        StringBuilder html = new StringBuilder();
        for (MonitorProperty monitorCase : monitorCases) {
            Map<String, Object> replaceMap = new HashMap<String, Object>();
            replaceMap.put("name", (Object) monitorCase.getName());
            replaceMap.put("value", (Object) monitorCase.getValue());
            String aNewRow = replaceTokens(TEMPLATE_FOR_MONITOR_PROPERTY, replaceMap);
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