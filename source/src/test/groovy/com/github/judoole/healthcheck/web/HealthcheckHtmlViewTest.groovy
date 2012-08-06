package com.github.judoole.healthcheck.web


import com.github.judoole.healthcheck.internal.dto.HealthcheckCase
import com.github.judoole.healthcheck.internal.HealthcheckProperty
import org.junit.Test
import com.github.judoole.healthcheck.internal.dto.HealthcheckSuite
import com.github.judoole.healthcheck.internal.dto.HealthcheckStacktrace
import static org.junit.matchers.JUnitMatchers.containsString
import static org.junit.Assert.assertThat

class HealthcheckHtmlViewTest {

    @Test
    void should_be_able_to_represent_as_html() {
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 1", failure: new HealthcheckStacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 2", error: new HealthcheckStacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new HealthcheckCase(name: "case 3. The good case"));
        given_healthchecksuite_has_property(new HealthcheckProperty(name: "Simple property", value: "Simple value"))
        then_the_html_should_contain('<title>My healthchecksuite</title>');
        then_the_html_should_contain('<tr><td><b>Simple property:</b></td><td>Simple value</td></tr>');
        then_the_html_should_contain('<tr><td>case 1</td><td class="warning"/></tr>');
        then_the_html_should_contain('<tr><td>case 2</td><td class="error"/></tr>');
        then_the_html_should_contain('<tr><td>case 3. The good case</td><td class="success"/></tr>');
    }

    void given_healthchecksuite_has_case(HealthcheckCase healthcheckCase) {
        suite.addCase(healthcheckCase);
    }

    void given_healthchecksuite_has_property(HealthcheckProperty healthcheckProperty) {
        suite.healthcheckProperties.add(healthcheckProperty);
    }

    void given_healthchecksuite_has_name(String name) {
        suite.name = name;
    }

    void then_the_html_should_contain(String string) {
        assertThat(view.process(suite), containsString(string));
    }

    HealthcheckHtmlView view = new HealthcheckHtmlView()
    HealthcheckSuite suite = new HealthcheckSuite(name: "", healthcheckCases: new HashSet(), healthcheckProperties: new HashSet());
}