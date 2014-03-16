package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.dto.Case;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;
import com.github.judoole.monitorino.web.util.MonitorinoTemplater;

import java.util.*;

import static com.github.judoole.monitorino.web.util.MonitorinoTemplater.replaceTokens;
import static com.github.judoole.monitorino.web.util.MonitorinoTemplater.template;

public class XmlView {
	private static final String TEMPLATE_CASE_ERROR = "    <testcase name=\"[name]\">\n" +
			"        <error message=\"[message]\">[stacktrace]</error>\n" +
			"    </testcase>";
	private static final String TEMPLATE_CASE_FAILURE = "<testcase name=\"[name]\">\n" +
			"        <failure message=\"[message]\"/>\n" +
			"    </testcase>";
	private static final String TEMPLATE_CASE_SUCCESS = "<testcase name=\"[name]\"/>";
	private static final String TEMPLATE_PROPERTY = "<property name=\"[name]\" value=\"[value]\"/>";

	public String process(MonitorinoSuite suite) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", suite.name);
		map.put("number_of_tests", suite.tests);
		map.put("number_of_skipped", suite.skipped);
		map.put("number_of_errors", suite.errors);
		map.put("number_of_failures", suite.failures);
		map.put("cases", cases(suite.testCases));
		map.put("properties", properties(suite.healthcheckProperties));

		return MonitorinoTemplater.replaceTokens(template("/com/github/judoole/healthcheckXmlViewTemplate.xml"), map);
	}

	private String cases(Set<Case> cases) {
		if (cases == null) return "";

		StringBuilder xml = new StringBuilder();
		for (Case testCase : cases) {
			String template = null;
			Map<String, Object> replaceMap = new HashMap<String, Object>();
			replaceMap.put("name", (Object) testCase.name);
			if (testCase.hasError()) {
				template = TEMPLATE_CASE_ERROR;
				replaceMap.put("message", testCase.error.message);
				replaceMap.put("stacktrace", testCase.error.stacktrace);
			} else if (testCase.hasFailure()) {
				template = TEMPLATE_CASE_FAILURE;
				replaceMap.put("message", testCase.failure.message);
			} else {
				template = TEMPLATE_CASE_SUCCESS;
			}
			String aNewRow = MonitorinoTemplater.replaceTokens(template, replaceMap);
			xml.append(aNewRow);
		}

		return xml.toString();

	}

	private String properties(Properties properties) {
		if (properties == null) return "";

		StringBuilder xml = new StringBuilder();
		Enumeration<?> keys = properties.propertyNames();
		while (keys.hasMoreElements()) {
			Map<String, Object> replaceMap = new HashMap<String, Object>();
			Object name = keys.nextElement();
			replaceMap.put("name", name);
			replaceMap.put("value", properties.getProperty((String) name));
			String aNewRow = replaceTokens(TEMPLATE_PROPERTY, replaceMap);
			xml.append(aNewRow);
		}

		return "\n<properties>\n" + xml.toString() + "\n</properties>";

	}
}
