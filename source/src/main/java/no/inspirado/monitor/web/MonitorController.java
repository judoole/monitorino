package no.inspirado.monitor.web;

import no.inspirado.monitor.internal.MonitorCaseRunner;
import no.inspirado.monitor.internal.MonitorSuiteAssembler;
import no.inspirado.monitor.internal.dto.MonitorSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
    Collection<MonitorCaseRunner> monitorCaseRunners;
    String name = "AppserverMonitor";

    @Autowired
    public void setMonitorCaseRunners(Collection<MonitorCaseRunner> monitorCaseRunners) {
        this.monitorCaseRunners = monitorCaseRunners;
    }

    @RequestMapping(value = {"/xml", "/junit"}, method = RequestMethod.GET, headers = "Accept=application/xml")
    @ResponseBody
    public String jUnitRapport() {
        return runSuite().asXml();
    }

    @RequestMapping(value = {"/", "/html"}, method = RequestMethod.GET, headers = "Accept=text/html")
    @ResponseBody
    public String htmlRapport() {
        return runSuite().asHtml();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    @ResponseBody
    public String iAmAlive() {
        return "Hello world. I am alive.";
    }

    public void setName(String name) {
        this.name = name;
    }

    MonitorSuite runSuite() {
        return new MonitorSuiteAssembler(name, monitorCaseRunners).run();
    }

}