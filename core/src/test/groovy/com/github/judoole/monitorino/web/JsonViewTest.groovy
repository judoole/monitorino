package com.github.judoole.monitorino.web

import com.github.judoole.monitorino.internal.dto.Case
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite
import com.github.judoole.monitorino.internal.dto.Stacktrace
import org.junit.Test

import static org.junit.Assert.assertThat
import static org.junit.matchers.JUnitMatchers.containsString

class JsonViewTest {
    @Test
    void should_be_able_to_represent_as_json() {
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new Case(name: "case 1", failure: new Stacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new Case(name: "case 2", error: new Stacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new Case(name: "case 3. The good case"));
        given_healthchecksuite_has_property("Simple property", "Simple value");
        given_healthchecksuite_has_property("some-other-property", "Some other value");
        then_the_json_should_contain('"name": "My healthchecksuite","tests": "3","skipped": "0","errors": "1","failures": "1"')
        then_the_json_should_contain('{"name": "case 1","failure": {"message": "Things went far from good"}}')
        then_the_json_should_contain('{"name": "case 3. The good case"}')
        then_the_json_should_contain('{"name": "case 2","error": {"message": "Things did not go so well no either","stacktrace": "Bla, bla, BLAM!"}}')
        then_the_json_should_contain('"healthcheckProperties": [{"some-other-property": "Some other value","Simple property": "Simple value"}]')
    }

    void given_healthchecksuite_has_case(Case healthcheckCase) {
        suite.addCase(healthcheckCase);
    }

    void given_healthchecksuite_has_property(String name, String value) {
        suite.healthcheckProperties.put(name, value);
    }

    void given_healthchecksuite_has_name(String name) {
        suite.name = name;
    }

    void then_the_json_should_contain(String string) {
        assertThat(view.process(suite, false), containsString(string));
    }

    JsonView view = new JsonView()
    MonitorinoSuite suite = new MonitorinoSuite(name: "", testCases: new HashSet(), healthcheckProperties: new HashSet());
}
