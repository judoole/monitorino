package com.github.judoole.healthcheck.web;

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.HealthcheckSuiteAssembler;
import com.github.judoole.healthcheck.internal.dto.HealthcheckSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;
import java.util.Set;

@Controller
@RequestMapping("/healthcheck")
public class HealthcheckController {
    private HealthcheckHtmlView htmlView = new HealthcheckHtmlView();
    private HealthcheckXmlView xmlView = new HealthcheckXmlView();
    Set<HealthcheckCaseRunner> healthcheckCaseRunners;
    Properties healthcheckProperties;

    String name = "AppserverHealthcheck";

    @Autowired(required = false)
    public void setHealthcheckCaseRunners(Set<HealthcheckCaseRunner> healthcheckCaseRunners) {
        this.healthcheckCaseRunners = healthcheckCaseRunners;
    }

    public void setProperties(Properties healthcheckProperties) {
        this.healthcheckProperties = healthcheckProperties;
    }

    @RequestMapping(value = {"/xml", "/junit"}, method = RequestMethod.GET)
    @ResponseBody
    public String jUnitRapport() {
        return xmlView.process(runSuite());
    }

    @RequestMapping(value = {"/", "/html"}, method = RequestMethod.GET)
    @ResponseBody
    public String htmlRapport() {
        return htmlView.process(runSuite());
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