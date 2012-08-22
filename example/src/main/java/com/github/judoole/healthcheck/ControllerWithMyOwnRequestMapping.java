package com.github.judoole.healthcheck;

import com.github.judoole.healthcheck.web.HealthcheckController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/smoketest")
public class ControllerWithMyOwnRequestMapping extends HealthcheckController {
}
