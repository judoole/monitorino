package com.github.judoole.monitorino.web;

import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.internal.SuiteAssembler;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;
import java.util.Set;

@Controller
@RequestMapping("/healthcheck")
public class MonitorinoController {
    private HtmlView htmlView = new HtmlView();
    private XmlView xmlView = new XmlView();
    Set<MonitorinoRunner> runners;
    Properties healthcheckProperties;

    String name = "AppserverHealthcheck";

    @Autowired(required = false)
    public void setRunners(Set<MonitorinoRunner> runners) {
        this.runners = runners;
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

    MonitorinoSuite runSuite() {
        return new SuiteAssembler(name, runners, healthcheckProperties).run();
    }

}