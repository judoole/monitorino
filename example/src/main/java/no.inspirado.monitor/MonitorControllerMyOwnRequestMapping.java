package no.inspirado.monitor;

import no.inspirado.monitor.web.MonitorController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/healthcheck")
public class MonitorControllerMyOwnRequestMapping extends MonitorController{
}
