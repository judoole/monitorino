package com.github.judoole.healthcheck;

import com.github.judoole.healthcheck.internal.HealthcheckCaseRunner;
import com.github.judoole.healthcheck.internal.HealthcheckSuiteAssembler;
import com.github.judoole.healthcheck.cases.HealthcheckThatFailes;
import com.github.judoole.healthcheck.cases.HealthcheckThatIsSuccess;
import com.github.judoole.healthcheck.cases.HealthcheckThatThrowsRuntimeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class WebappHealthcheckHttpServletExample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        Collection<HealthcheckCaseRunner> list = new ArrayList<HealthcheckCaseRunner>();
        list.add(new HealthcheckThatThrowsRuntimeException());
        list.add(new HealthcheckThatFailes());
        list.add(new HealthcheckThatIsSuccess());
        out.println(new HealthcheckSuiteAssembler("My first stab at it", list, null).run().asXml());
    }
}
