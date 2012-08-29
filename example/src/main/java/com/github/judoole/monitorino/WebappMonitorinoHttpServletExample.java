package com.github.judoole.monitorino;

import com.github.judoole.monitorino.internal.MonitorinoRunner;
import com.github.judoole.monitorino.internal.SuiteAssembler;
import com.github.judoole.monitorino.cases.AssertTwoPlusTwoIsFour;
import com.github.judoole.monitorino.cases.EverythingIsOk;
import com.github.judoole.monitorino.internal.dto.MonitorinoSuite;
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
        Collection<MonitorinoRunner> list = new ArrayList<MonitorinoRunner>();
        list.add(new AssertTwoPlusTwoIsFour());
        list.add(new EverythingIsOk());
        MonitorinoSuite suite = new SuiteAssembler("Monitor from HttpServlet", list, null).run();
        out.println(new HtmlView().process(suite));
    }
}
