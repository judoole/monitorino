package com.github.judoole.monitorino.web

import org.junit.Test
import static org.junit.Assert.assertThat
import static org.junit.matchers.JUnitMatchers.containsString
import com.github.judoole.monitorino.internal.dto.HealthcheckCase
import com.github.judoole.monitorino.internal.dto.HealthcheckStacktrace
import com.github.judoole.monitorino.internal.dto.HealthcheckSuite

class HtmlViewTest {

    @Test
    void should_be_able_to_represent_as_html() {
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 1", failure: new HealthcheckStacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 2", error: new HealthcheckStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 3. The good case"));
        given_healthchecksuite_has_property("Simple property", "Simple value")
        then_the_html_should_contain('<title>My healthchecksuite</title>');
        then_the_html_should_contain('<tr><td><b>Simple property:</b></td><td>Simple value</td></tr>');
        then_the_html_should_contain('<tr><td>case 1</td><td class="warning"/></tr>');
        then_the_html_should_contain('<tr><td>case 2</td><td class="error"/></tr>');
        then_the_html_should_contain('<tr><td>case 3. The good case</td><td class="success"/></tr>');
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

    void then_the_html_should_contain(String string) {
        assertThat(view.process(suite), containsString(string));
    }

    HtmlView view = new HtmlView()
    HealthcheckSuite suite = new HealthcheckSuite(name: "", healthcheckCases: new HashSet(), healthcheckProperties: new HashSet());
}
