package com.github.judoole.healthcheck.web

import com.github.judoole.healthcheck.internal.dto.HealthcheckCase

import com.github.judoole.healthcheck.internal.dto.HealthcheckSuite

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnit44Runner
import static org.hamcrest.core.IsNot.not
import static org.junit.Assert.assertThat
import static org.mockito.BDDMockito.given
import static org.hamcrest.core.IsNull.notNullValue
import static org.hamcrest.core.IsEqual.equalTo
import static org.hamcrest.core.IsNull.nullValue
import static org.junit.matchers.JUnitMatchers.containsString

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner
import com.github.judoole.healthcheck.internal.dto.HealthcheckStacktrace

@RunWith(MockitoJUnit44Runner.class)
class HealthcheckControllerTest {

    @Test
    void should_create_a_healthcheck_suite() {
        given_controller_is_created();
        given_runner_returns_successful_case();
        when_I_run_the_controller();
        then_I_shall_receive_a_suite();
        then_the_suite_should_have_a_name();
        then_the_siute_should_have_correct_number_of_tests();
        then_the_siute_should_have_recorded_the_time_spent();
    }

    @Test
    void should_not_crash_even_if_runner_throws_exception() {
        given_controller_is_created();
        given_runner_throws_exception();
        when_I_run_the_controller();
        then_I_shall_receive_a_suite();
        then_the_suite_should_have_errors();
        then_case_should_have_stacktrace();
    }

    @Test
    void should_count_number_of_failing_cases() {
        given_controller_is_created();
        given_runner_returns_case_that_has_failed();
        when_I_run_the_controller();
        then_I_shall_receive_a_suite();
        then_the_suite_should_have_failures();
    }

    @Test
    void should_autowire_all_runners_to_controller() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationHealthcheckControllerTest.class);
        HealthcheckController controller = context.getBean(HealthcheckController.class);
        assertThat(controller, notNullValue());
        assertThat(controller.healthcheckCaseRunners.size(), not(equalTo(0)));
    }

    @Test
    void should_autowire_all_properties_to_controller() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationHealthcheckControllerTest.class);
        HealthcheckController controller = context.getBean(HealthcheckController.class);
        assertThat(controller, notNullValue());
        assertThat(controller.healthcheckProperties.size(), not(equalTo(0)));
    }

    private void given_controller_is_created() {
        controller = new HealthcheckController(healthcheckCaseRunners:[healthcheckCaseRunner]);
    }

    private void given_runner_returns_successful_case() {
        given(healthcheckCaseRunner.run()).willReturn(new HealthcheckCase())
    }

    private void given_runner_throws_exception() {
        given(healthcheckCaseRunner.run()).willThrow(new RuntimeException(EXPECTED_EXCEPTION_MESSAGE))
    }

    private void given_runner_returns_case_that_has_failed() {
        HealthcheckCase failedHealthcheckCase = new HealthcheckCase();
        failedHealthcheckCase.failure = new HealthcheckStacktrace();
        given(healthcheckCaseRunner.run()).willReturn(failedHealthcheckCase);
    }

    private void when_I_run_the_controller() {
        suite = controller.runSuite()
    }

    private def then_I_shall_receive_a_suite() {
        assertThat(suite, notNullValue())
    }

    private void then_the_suite_should_have_a_name() {
        assertThat(suite.name, notNullValue());
    }

    private void then_the_siute_should_have_correct_number_of_tests() {
        assertThat(suite.tests, equalTo(1));
    }

    private void then_the_siute_should_have_recorded_the_time_spent() {
        assertThat(suite.time, not(nullValue()));
    }

    private void then_the_suite_should_have_errors() {
        assertThat(suite.errors, equalTo(1));
        assertThat(suite.failures, equalTo(0));
    }

    private void then_the_suite_should_have_failures() {
        assertThat(suite.errors, equalTo(0));
        assertThat(suite.failures, equalTo(1));
    }

    private void then_case_should_have_stacktrace() {
        Collection<HealthcheckCase> cases = suite.healthcheckCases;
        assertThat(cases, notNullValue());
        assertThat(cases.size(), not(0));
        HealthcheckStacktrace error = cases.iterator().next().error
        assertThat(error, notNullValue());
        assertThat(error.message, equalTo(EXPECTED_EXCEPTION_MESSAGE));
        assertThat(error.stacktrace, containsString(getClass().name));
    }

    private static final String EXPECTED_EXCEPTION_MESSAGE = "Exception made by mockito for unit test"
    @Mock
    HealthcheckCaseRunner healthcheckCaseRunner;
    HealthcheckController controller;
    HealthcheckSuite suite;

}