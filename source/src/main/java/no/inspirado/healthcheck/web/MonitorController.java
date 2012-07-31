package no.inspirado.healthcheck.web;

import no.inspirado.healthcheck.internal.MonitorCaseRunner;
import no.inspirado.healthcheck.internal.MonitorSuiteAssembler;
import no.inspirado.healthcheck.internal.dto.MonitorProperty;
import no.inspirado.healthcheck.internal.dto.MonitorSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
    Set<MonitorCaseRunner> monitorCaseRunners;
    Set<MonitorProperty> monitorProperties;

    String name = "AppserverMonitor";

    @Autowired
    public void setMonitorCaseRunners(Set<MonitorCaseRunner> monitorCaseRunners) {
        this.monitorCaseRunners = monitorCaseRunners;
    }

    @Autowired
    public void setMonitorProperties(Set<MonitorProperty> monitorProperties) {
        this.monitorProperties = monitorProperties;
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

    MonitorSuite runSuite() {
        return new MonitorSuiteAssembler(name, monitorCaseRunners, monitorProperties).run();
    }

}