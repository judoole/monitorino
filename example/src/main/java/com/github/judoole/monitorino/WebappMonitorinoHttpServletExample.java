package com.github.judoole.monitorino;

import com.github.judoole.monitorino.internal.HealthcheckCaseRunner;
import com.github.judoole.monitorino.internal.HealthcheckSuiteAssembler;
import com.github.judoole.monitorino.cases.AssertTwoPlusTwoIsFour;
import com.github.judoole.monitorino.cases.EverythingIsOk;
import com.github.judoole.monitorino.internal.dto.TestSuite;
import com.github.judoole.monitorino.web.HtmlView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class WebappMonitorinoHttpServletExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        Collection<HealthcheckCaseRunner> list = new ArrayList<HealthcheckCaseRunner>();
        list.add(new AssertTwoPlusTwoIsFour());
        list.add(new EverythingIsOk());
        TestSuite suite = new HealthcheckSuiteAssembler("Monitor from HttpServlet", list, null).run();
        out.println(new HtmlView().process(suite));
    }
}
