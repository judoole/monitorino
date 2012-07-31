package no.inspirado.healthcheck.web;

import no.inspirado.healthcheck.internal.HealthcheckCaseRunner;
import no.inspirado.healthcheck.internal.HealthcheckSuiteAssembler;
import no.inspirado.healthcheck.internal.dto.HealthcheckProperty;
import no.inspirado.healthcheck.internal.dto.HealthcheckSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping("/healthcheck")
public class HealthcheckController {
    Set<HealthcheckCaseRunner> healthcheckCaseRunners;
    Set<HealthcheckProperty> healthcheckProperties;

    String name = "AppserverHealthcheck";

    @Autowired
    public void setHealthcheckCaseRunners(Set<HealthcheckCaseRunner> healthcheckCaseRunners) {
        this.healthcheckCaseRunners = healthcheckCaseRunners;
    }

    @Autowired
    public void setHealthcheckProperties(Set<HealthcheckProperty> healthcheckProperties) {
        this.healthcheckProperties = healthcheckProperties;
    }

    @RequestMapping(value = {"/xml", "/junit"}, method = RequestMethod.GET)
    @ResponseBody
    public String jUnitRapport() {
        return runSuite().asXml();
    }

    @RequestMapping(value = {"/", "/html"}, method = RequestMethod.GET)
    @ResponseBody
    public String htmlRapport() {
        return runSuite().asHtml();
    }

    @RequestMapping(value = {"/xml", "/junit", "/", "/html"}, method = RequestMethod.HEAD)
    @ResponseBody
    public String iAmAlive() {
        return "Hello world. I am alive.";
    }

    public void setName(String name) {
        this.name = name;
    }

    HealthcheckSuite runSuite() {
        return new HealthcheckSuiteAssembler(name, healthcheckCaseRunners, healthcheckProperties).run();
    }

}