package com.github.judoole.monitorino.web

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.github.judoole.monitorino.internal.dto.Case
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite
import com.github.judoole.monitorino.internal.dto.Stacktrace
import org.junit.Test

import static org.hamcrest.core.IsEqual.equalTo
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertThat

class JsonViewTest {
    @Test
    void should_be_able_to_represent_as_json() {
        given_healthchecksuite_has_name("My healthchecksuite");
        given_healthchecksuite_has_case(new Case(name: "case 1", failure: new Stacktrace(message: "Things went far from good")));
        given_healthchecksuite_has_case(new Case(name: "case 2", error: new Stacktrace(message: "Things did not go so well no either", stacktrace: "Bla, bla, BLAM!")));
        given_healthchecksuite_has_case(new Case(name: "case 3. The good case"));
        given_healthchecksuite_has_property("Simple property", "Simple value");
        then_the_json_should_be_valid_suite(view.process(suite));
        and_the_suite_should_be_named("My healthchecksuite");
        and_the_suites_test_cases_should_be_a_list_of_size(3);
        and_the_suite_should_have_healthcheckproperties()
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

    void then_the_json_should_be_valid_suite(String json) {
        suiteAsJson = objectMapper.readValue(json, JsonNode.class);
        assertNotNull(suiteAsJson)
    }

    void and_the_suite_should_be_named(String name) {
        assertThat(suiteAsJson.get("name").asText(), equalTo(name));
    }

    void and_the_suites_test_cases_should_be_a_list_of_size(int size) {
        assertThat(suiteAsJson.get("testCases").isArray(), equalTo(true));
        ArrayNode arrayNode = suiteAsJson.get("testCases");
        assertThat(arrayNode.size(), equalTo(size));
    }

    void and_the_suite_should_have_healthcheckproperties() {
        JsonNode healthCheckProperties = suiteAsJson.get("healthcheckProperties");
        assertThat(healthCheckProperties.isContainerNode(), equalTo(true));
        assertThat(healthCheckProperties.get("Simple property").isValueNode(), equalTo(true));
        assertThat(healthCheckProperties.get("Simple property").asText(), equalTo("Simple value"));
    }

    ObjectMapper objectMapper = new ObjectMapper();
    JsonView view = new JsonView()
    MonitorinoSuite suite = new MonitorinoSuite(name: "", testCases: new HashSet(), healthcheckProperties: new HashSet());
    JsonNode suiteAsJson;
}
