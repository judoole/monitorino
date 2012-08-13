package com.github.judoole.healthcheck.web

import com.github.judoole.healthcheck.internal.dto.HealthcheckCase
import com.github.judoole.healthcheck.internal.dto.HealthcheckStacktrace
import com.github.judoole.healthcheck.internal.dto.HealthcheckSuite
import org.junit.Test
import static org.junit.Assert.assertThat
import static org.junit.matchers.JUnitMatchers.containsString

class HealthcheckXmlViewTest {
    @Test
    void should_be_able_to_represent_as_xml() {
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case1", failure: new HealthcheckStacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 2", error: new HealthcheckStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 3. The good case"));
        given_healthchecksuite_has_property("Simple property", "Simple value")
        then_the_xml_should_contain('<testsuite name="My healthchecksuite" tests="3" skipped="0" errors="1" failures="1">');
        then_the_xml_should_contain('<testcase name="case1"><failure message="Things went far from good"/></testcase>');
        then_the_xml_should_contain('<testcase name="case 2"><error message="Things did not go so well no either">Bla, bla, BLAM!</error></testcase>');
        then_the_xml_should_contain('<testcase name="case 3. The good case"/>');
        then_the_xml_should_contain('<property name="Simple property" value="Simple value"/></properties>');
    }

    void given_healthchecksuite_has_case(HealthcheckCase healthcheckCase) {
        suite.addCase(healthcheckCase);
    }

    void given_healthchecksuite_has_property(String name, String value) {
        suite.healthcheckProperties.put(name, value);
    }

    void given_healthchecksuite_has_name(String name) {
        suite.name = name;
    }

    void then_the_xml_should_contain(String string) {
        assertThat(view.process(suite, false), containsString(string));
    }

    HealthcheckXmlView view = new HealthcheckXmlView()
    HealthcheckSuite suite = new HealthcheckSuite(name: "", healthcheckCases: new HashSet(), healthcheckProperties: new HashSet());
}
