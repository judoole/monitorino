package no.inspirado.monitor.internal.dto

import org.junit.Test
import static org.junit.Assert.assertThat
import static org.junit.matchers.JUnitMatchers.containsString

class MonitorSuiteTest {

    @Test
    void should_be_able_to_represent_as_xml(){
        given_monitorsuite_has_name("My monitorsuite");
        given_monitorsuite_has_case(new MonitorCase(name: "case1", failure: new MonitorStacktrace(message: "Things went far from good")));
        given_monitorsuite_has_case(new MonitorCase(name: "case 2", error: new MonitorStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_monitorsuite_has_case(new MonitorCase(name: "case 3. The good case"));
        then_the_xml_should_contain('<testsuite name="My monitorsuite" tests="3" skipped="0" errors="1" failures="1">');
        then_the_xml_should_contain('<testcase name="case1"><failure message="Things went far from good"/></testcase>');
        then_the_xml_should_contain('<testcase name="case 2"><error message="Things did not go so well no either">Bla, bla, BLAM!</error></testcase>');
        then_the_xml_should_contain('<testcase name="case 3. The good case"/>');
    }

    @Test
    void should_be_able_to_represent_as_html(){
        given_monitorsuite_has_name("My monitorsuite");
        given_monitorsuite_has_case(new MonitorCase(name: "case 1", failure: new MonitorStacktrace(message: "Things went far from good")));
        given_monitorsuite_has_case(new MonitorCase(name: "case 2", error: new MonitorStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_monitorsuite_has_case(new MonitorCase(name: "case 3. The good case"));
        then_the_html_should_contain('<title>My monitorsuite</title>');
        then_the_html_should_contain('<tr><td>case 1</td><td class="warning"/></tr>');
        then_the_html_should_contain('<tr><td>case 2</td><td class="error"/></tr>');
        then_the_html_should_contain('<tr><td>case 3. The good case</td><td class="success"/></tr>');
    }


    void given_monitorsuite_has_case(MonitorCase monitorCase) {
        msg.addCase(monitorCase);
    }

    void given_monitorsuite_has_name(String name) {
        msg.name = name;
    }

    void then_the_xml_should_contain(String string) {
        assertThat(msg.asXml(false), containsString(string));
    }

    void then_the_html_should_contain(String string) {
        assertThat(msg.asHtml(), containsString(string));
    }

    MonitorSuite msg = new MonitorSuite(name: "", monitorCases: new ArrayList());
}
