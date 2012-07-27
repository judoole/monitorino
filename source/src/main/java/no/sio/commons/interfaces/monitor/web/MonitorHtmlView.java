package no.sio.commons.interfaces.monitor.web;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorCase;
import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
    private static final String TEMPLATE_FOR_MONITOR_CASER = "<tr><td>[navn]</td><td class=\"[erSuccess]\"/></tr>\n";

    public String process(MonitorSuite suite) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("navn", suite.navn);
        map.put("caser", lagHtmlForCaser(suite.monitorCases));

        return replaceTokens(template(), map);
    }

    private String template() {
        try {
            Resource templateResource = new ClassPathResource("no/sio/commons/interfaces/monitor/web/monitorViewTemplate.html");
            return IOUtils.toString(templateResource.getInputStream(), "UTF-8");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String lagHtmlForCaser(Collection<MonitorCase> monitorCases) {
        StringBuilder html = new StringBuilder();
        for (MonitorCase monitorCase : monitorCases) {
            Map<String, Object> replaceMap = new HashMap<String, Object>();
            replaceMap.put("navn", (Object) monitorCase.navn);
            if (monitorCase.harError()) {
                replaceMap.put("erSuccess", "error");
            } else if(monitorCase.harFeil()){
                replaceMap.put("erSuccess", "warning");
            }
            else {
                replaceMap.put("erSuccess", "success");
            }
            String enNyRow = replaceTokens(TEMPLATE_FOR_MONITOR_CASER, replaceMap);
            html.append(enNyRow);
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