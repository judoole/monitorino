package no.inspirado.healthcheck;

import no.inspirado.healthcheck.web.HealthcheckController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/healthcheck")
public class HealthcheckControllerMyOwnRequestMapping extends HealthcheckController {
}
