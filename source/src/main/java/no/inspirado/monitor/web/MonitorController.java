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

@Controller
public class MonitorController {
    private Collection<MonitorCaseRunner> monitorCaseRunners;
    private final static String name = "AppserverMonitor";

    @Autowired
    public MonitorController(Collection<MonitorCaseRunner> monitorCaseRunners) {
        this.monitorCaseRunners = monitorCaseRunners;
    }

    @RequestMapping(value = {"/xml", "/junit"}, method = RequestMethod.GET)
    @ResponseBody
    public String jUnitRapport() {
        return runSuite().asXml();// new ModelAndView(xStreamMarshaller(), BindingResult.MODEL_KEY_PREFIX + "testsuite", runSuite());
    }

    @RequestMapping(value = {"/", "/html"}, method = RequestMethod.GET)
    @ResponseBody
    public String htmlRapport() {
        return runSuite().asHtml();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    @ResponseBody
    public String iAmAlive() {
        return "Hello world. I am alive.";
    }

    MonitorSuite runSuite() {
        return new MonitorSuiteAssembler(name, monitorCaseRunners).run();
    }

}