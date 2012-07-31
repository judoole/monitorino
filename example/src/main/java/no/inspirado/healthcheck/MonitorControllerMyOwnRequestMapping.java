package no.inspirado.healthcheck;

import no.inspirado.healthcheck.web.MonitorController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/healthcheck")
public class MonitorControllerMyOwnRequestMapping extends MonitorController{
}
