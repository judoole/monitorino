package no.inspirado.healthcheck;

import no.inspirado.healthcheck.web.HealthcheckController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/smoketest")
public class HealthcheckControllerMyOwnRequestMapping extends HealthcheckController {
}
