package no.sio.commons.interfaces.monitor.web;

import no.sio.commons.interfaces.monitor.facade.dto.MonitorSuite;
import no.sio.commons.interfaces.monitor.internal.MonitorCaseRunner;
import no.sio.commons.interfaces.monitor.internal.MonitorSuiteAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Collection;

@Controller
public class MonitorController {
    private Collection<MonitorCaseRunner> monitorCaseRunners;
    private final static String navn = "AppserverMonitor";

    @Autowired
    public MonitorController(Collection<MonitorCaseRunner> monitorCaseRunners) {
        this.monitorCaseRunners = monitorCaseRunners;
    }

    @RequestMapping(value = {"/xml", "/junit"}, method = RequestMethod.GET)
    public ModelAndView jUnitRapport() {
        return new ModelAndView(xStreamMarshaller(), BindingResult.MODEL_KEY_PREFIX + "testsuite", kjorSuite());
    }

    @RequestMapping(value = {"/", "/html"}, method = RequestMethod.GET)
    @ResponseBody
    public String htmlRapport() {
        return new MonitorHtmlView().process(kjorSuite());
    }

    @RequestMapping(method = RequestMethod.HEAD)
    @ResponseBody
    public String iAmAlive() {
        return "Hello world. I am alive.";
    }

    MonitorSuite kjorSuite() {
        return new MonitorSuiteAssembler(navn, monitorCaseRunners).kjor();
    }

    private MarshallingView xStreamMarshaller() {
        MarshallingView view = new MarshallingView();
        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAutodetectAnnotations(true);
        view.setMarshaller(marshaller);
        return view;
    }
}