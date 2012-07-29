package no.inspirado.monitor;

import no.inspirado.monitor.cases.MonitorCaseThatFailes;
import no.inspirado.monitor.cases.MonitorCaseThatGoesOk;
import no.inspirado.monitor.cases.MonitorCaseThatThrowsRuntimeException;
import no.inspirado.monitor.internal.MonitorCaseRunner;
import no.inspirado.monitor.internal.MonitorSuiteAssembler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class WebappMonitorHttpServletExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        Collection<MonitorCaseRunner> list = new ArrayList<MonitorCaseRunner>();
        list.add(new MonitorCaseThatThrowsRuntimeException());
        list.add(new MonitorCaseThatFailes());
        list.add(new MonitorCaseThatGoesOk());
        out.println(new MonitorSuiteAssembler("My first stab at it", list).run().asHtml());
    }
}
