package no.inspirado.healthcheck.internal.dto

import org.junit.Test
import static org.junit.Assert.assertThat
import static org.junit.matchers.JUnitMatchers.containsString

class HealthcheckSuiteTest {

    @Test
    void should_be_able_to_represent_as_xml(){
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case1", failure: new HealthcheckStacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 2", error: new HealthcheckStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 3. The good case"));
        given_healthchecksuite_has_property(new HealthcheckProperty(name:"Simple property", value:"Simple value"))
        then_the_xml_should_contain('<testsuite name="My healthchecksuite" tests="3" skipped="0" errors="1" failures="1">');
        then_the_xml_should_contain('<testcase name="case1"><failure message="Things went far from good"/></testcase>');
        then_the_xml_should_contain('<testcase name="case 2"><error message="Things did not go so well no either">Bla, bla, BLAM!</error></testcase>');
        then_the_xml_should_contain('<testcase name="case 3. The good case"/>');
        then_the_xml_should_contain('<property name="Simple property" value="Simple value"/></properties>');
    }

    @Test
    void should_be_able_to_represent_as_html(){
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 1", failure: new HealthcheckStacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 2", error: new HealthcheckStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 3. The good case"));
        given_healthchecksuite_has_property(new HealthcheckProperty(name:"Simple property", value:"Simple value"))
        then_the_html_should_contain('<title>My healthchecksuite</title>');
        then_the_html_should_contain('<tr><td><b>Simple property:</b></td><td>Simple value</td></tr>');
        then_the_html_should_contain('<tr><td>case 1</td><td class="warning"/></tr>');
        then_the_html_should_contain('<tr><td>case 2</td><td class="error"/></tr>');
        then_the_html_should_contain('<tr><td>case 3. The good case</td><td class="success"/></tr>');
    }


    void given_healthchecksuite_has_case(HealthcheckCase healthcheckCase) {
        msg.addCase(healthcheckCase);
    }

    void given_healthchecksuite_has_property(HealthcheckProperty healthcheckProperty) {
        msg.healthcheckProperties.add(healthcheckProperty);
    }

    void given_healthchecksuite_has_name(String name) {
        msg.name = name;
    }

    void then_the_xml_should_contain(String string) {
        assertThat(msg.asXml(false), containsString(string));
    }

    void then_the_html_should_contain(String string) {
        assertThat(msg.asHtml(), containsString(string));
    }

    HealthcheckSuite msg = new HealthcheckSuite(name: "", healthcheckCases: new HashSet(), healthcheckProperties: new HashSet());
}
